package com.example.storeapp.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.storeapp.R
import com.example.storeapp.databinding.FragmentHomeBinding
import com.example.storeapp.model.response.ProductsResponse
import com.example.storeapp.view.BaseComponents.BaseFragment
import com.example.storeapp.view.Navigators.HomeNavigator
import com.example.storeapp.view.viewModel.fragments.HomeFragmentViewModel

private const val TAG = "HomeFragment"

class HomeFragment : BaseFragment(), HomeNavigator {
    lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewModel = HomeFragmentViewModel(requireActivity(), viewLifecycleOwner, this)
        binding.vm = viewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.productsAPI(requireActivity())

    }

    override fun onProductsResponse(response: List<ProductsResponse>) {
        showToast(response.size.toString())
        binding.textView.text = response[0].title

    }

    override fun showProgressBar() {

    }

    override fun hideProgressBar() {

    }
}