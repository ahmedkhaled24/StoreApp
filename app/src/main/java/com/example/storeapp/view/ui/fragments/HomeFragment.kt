package com.example.storeapp.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
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

class HomeFragment : BaseFragment(), HomeNavigator, ClickOnItemProduct {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeFragmentViewModel
    private lateinit var productsAdapter: ProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewModel = HomeFragmentViewModel(requireActivity(), viewLifecycleOwner, this)
        binding.vm = viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.productsAPI(requireActivity())
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
        runAnimationAgain()
    }


    override fun onProductsResponse(response: MutableList<ProductData>) {
        binding.numOfApparelTv.text = getString(R.string.apparel, response.size.toString())
        productsRecycler(response)
    }

    private fun runAnimationAgain() {
        val controller: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(requireContext(), R.anim.layout_animation_down_to_up)
        binding.recyclerProducts.layoutAnimation = controller
        binding.recyclerProducts.scheduleLayoutAnimation()
    }

    override fun showProgressBar() {
        binding.progressLayoutId.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressLayoutId.visibility = View.GONE
    }

    override fun clickOnParentItem(id: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProductDetailsFragment(id))
    }
}