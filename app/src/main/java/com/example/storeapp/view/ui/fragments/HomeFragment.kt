package com.example.storeapp.view.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.storeapp.R
import com.example.storeapp.databinding.FragmentHomeBinding
import com.example.storeapp.model.custom.ProductData
import com.example.storeapp.view.BaseComponents.BaseFragment
import com.example.storeapp.view.Navigators.HomeNavigator
import com.example.storeapp.view.ui.adapters.products.ClickOnItemProduct
import com.example.storeapp.view.ui.adapters.products.ProductsAdapter
import com.example.storeapp.view.viewModel.fragments.HomeFragmentViewModel
import java.util.*


class HomeFragment : BaseFragment(), HomeNavigator, ClickOnItemProduct {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var productData: MutableList<ProductData>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewModel = HomeFragmentViewModel(requireActivity(), viewLifecycleOwner, this)
        binding.vm = viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.productsAPI(requireActivity())
        searchByTitle()
        listenerViews()
    }



    override fun onProductsResponse(response: MutableList<ProductData>) {
        productData = response
        binding.numOfApparelTv.text = getString(R.string.apparel, response.size.toString())
        productsRecycler(response)
    }


    override fun showProgressBar() {
        binding.progressLayoutId.visibility = View.VISIBLE
    }


    override fun hideProgressBar() {
        binding.progressLayoutId.visibility = View.GONE
    }


    private fun productsRecycler(data: MutableList<ProductData>) {
        productsAdapter = ProductsAdapter(this)
        productsAdapter.setData(data)
        binding.recyclerProducts.layoutManager = GridLayoutManager(
            binding.recyclerProducts.context,
            2,
            GridLayoutManager.VERTICAL,
            false
        )
        binding.recyclerProducts.adapter = productsAdapter
        //add animation
        val controller: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(requireContext(),
            R.anim.layout_animation_down_to_up)
        binding.recyclerProducts.layoutAnimation = controller
        binding.recyclerProducts.scheduleLayoutAnimation()
    }


    override fun clickOnParentItem(id: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(id))
    }


    private fun searchByTitle() {
        binding.editTextSearch.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            override fun afterTextChanged(editable: Editable) {
                filter(editable.toString())
            }
        })
    }


    private fun filter(text: String) {
        val filteredTitle = ArrayList<ProductData>()
        productData.filterTo(filteredTitle) {
            it.title.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))
        }
        binding.numOfApparelTv.text = getString(R.string.apparel, filteredTitle.size.toString())
        productsAdapter.setData(filteredTitle)
    }


    private fun listenerViews() {
        binding.searchIv.setOnClickListener {
            binding.searchIv.visibility = View.GONE
            binding.textView.visibility = View.GONE
            binding.editTextSearch.visibility = View.VISIBLE
            binding.closeSearchIv.visibility = View.VISIBLE
        }

        binding.closeSearchIv.setOnClickListener {
            binding.editTextSearch.text.clear()
            binding.editTextSearch.onEditorAction(EditorInfo.IME_ACTION_DONE)
            binding.closeSearchIv.visibility = View.GONE
            binding.editTextSearch.visibility = View.GONE
            binding.textView.visibility = View.VISIBLE
            binding.searchIv.visibility = View.VISIBLE
        }
    }


    override fun onResume() {
        super.onResume()
        binding.editTextSearch.text.clear()
    }
}