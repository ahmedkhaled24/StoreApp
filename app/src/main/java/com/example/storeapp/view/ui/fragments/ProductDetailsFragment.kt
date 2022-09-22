package com.example.storeapp.view.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.storeapp.R
import com.example.storeapp.databinding.FragmentProductDetailsBinding
import com.example.storeapp.model.custom.ProductData
import com.example.storeapp.view.BaseComponents.BaseFragment
import com.example.storeapp.view.Navigators.ProductDetailsNavigator
import com.example.storeapp.view.viewModel.fragments.ProductDetailsViewModel
import com.squareup.picasso.Picasso

class ProductDetailsFragment : BaseFragment(), ProductDetailsNavigator {

    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var viewModel: ProductDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_details, container, false)
        viewModel = ProductDetailsViewModel(requireActivity(), viewLifecycleOwner, this)
        binding.vm = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get product Id
        val productId: ProductDetailsFragmentArgs by navArgs()
        //call api
        viewModel.productDetailsAPI(requireContext(), productId.id)

        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onProductDetailsResponse(productData: ProductData) {
        Picasso.get().load(productData.image).into(binding.imageProductDetails)
        binding.titleTv.text = productData.title
        binding.descriptionTv.text = productData.description
        binding.priceTv.text = getString(R.string.price, productData.price.toString())
        binding.rateTv.text = getString(R.string.num_ratings, productData.rating.toString())
    }

    override fun showProgressBar() {
        binding.progressLayoutId.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        binding.progressLayoutId.visibility = View.GONE
    }
}