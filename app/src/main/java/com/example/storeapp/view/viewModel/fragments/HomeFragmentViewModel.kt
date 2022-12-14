package com.example.storeapp.view.viewModel.fragments

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.example.storeapp.helpers.CustomDialog
import com.example.storeapp.model.custom.ProductData
import com.example.storeapp.view.Navigators.HomeNavigator
import com.example.storeapp.view.BaseComponents.BaseViewModel

private const val TAG = "HomeFragmentViewModel"

class HomeFragmentViewModel(
    activity: FragmentActivity,
    private var viewLifecycleOwner: LifecycleOwner,
    private var navigator: HomeNavigator
) : BaseViewModel(activity) {

    //custom product
    private var productData: MutableList<ProductData> = ArrayList()


    fun productsAPI(activity: FragmentActivity) {
        navigator.showProgressBar()
        mUserViewModel.productsViewM().observe(viewLifecycleOwner) { dataResource ->
            // handle success
            dataResource.data?.getContentIfNotHandled()?.let { response ->
                for (i in response.indices) {
                    for (n in 0 until 1) {
                        productData.add(
                            ProductData(
                                response[i].id,
                                response[i].image,
                                response[i].title,
                                null,
                                response[i].price
                            )
                        )

                    }
                }
                navigator.onProductsResponse(productData)
                navigator.hideProgressBar()
            }
            // handle error
            dataResource.error?.getContentIfNotHandled()?.let {
                navigator.hideProgressBar()
                CustomDialog.showSuccessDialog(activity, activity.layoutInflater, it, -1)
                Log.d(TAG, "productsAPI: $it")
            }
        }
    }

}