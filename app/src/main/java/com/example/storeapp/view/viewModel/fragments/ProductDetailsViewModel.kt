package com.example.storeapp.view.viewModel.fragments

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.example.storeapp.helpers.CustomDialog
import com.example.storeapp.model.custom.ProductData
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

    fun productDetailsAPI(activity: FragmentActivity, id: Int) {
        navigator.showProgressBar()
        mUserViewModel.productDetailsViewM(id).observe(viewLifecycleOwner) { dataResource ->
            // handle success
            dataResource.data?.getContentIfNotHandled()?.let { response ->
                // set data in data class
                productData =
                    ProductData(
                        response.id,
                        response.image,
                        response.title,
                        response.description,
                        response.price,
                        response.rating.rate
                    )
                // send data for views by interface
                navigator.onProductDetailsResponse(productData)
                //hide progressbar
                navigator.hideProgressBar()
            }
            // handle error
            dataResource.error?.getContentIfNotHandled()?.let {
                navigator.hideProgressBar()
                CustomDialog.showSuccessDialog(activity, activity.layoutInflater, it, -1)
                Log.d(TAG, "productDetailsAPI: $it")
            }
        }
    }
}