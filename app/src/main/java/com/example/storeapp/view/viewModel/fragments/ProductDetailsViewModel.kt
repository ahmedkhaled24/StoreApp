package com.example.storeapp.view.viewModel.fragments

import android.content.Context
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.example.storeapp.model.ProductData
import com.example.storeapp.view.BaseComponents.BaseViewModel
import com.example.storeapp.view.Navigators.ProductDetailsNavigator

private const val TAG = "ProductDetailsViewModel"

class ProductDetailsViewModel(
    activity: FragmentActivity,
    private var viewLifecycleOwner: LifecycleOwner,
    private var navigator: ProductDetailsNavigator
) : BaseViewModel(activity) {

    //custom product
    private lateinit var productData: ProductData

    fun productDetailsAPI(context: Context, id: Int) {
        navigator.showProgressBar()
        mUserViewModel.productDetailsViewM(id).observe(viewLifecycleOwner) { dataResource ->
            // handle success
            dataResource.data?.getContentIfNotHandled()?.let { response ->
                // set data in data class
                productData =
                    ProductData(response.id, response.image, response.title, response.price)
                // send data for views by interface
                navigator.onProductDetailsResponse(productData)
                //hide progressbar
                navigator.hideProgressBar()
            }
            // handle error
            dataResource.error?.getContentIfNotHandled()?.let {
                navigator.hideProgressBar()
                showToast(context, it)
                Log.d(TAG, "productDetailsAPI: $it")
            }
        }
    }

}