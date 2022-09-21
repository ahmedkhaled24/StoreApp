package com.example.storeapp.view.viewModel.fragments

import android.content.Context
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.example.storeapp.model.ProductData
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


    fun productsAPI(context: Context) {
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
                showToast(context, "Failed")
                Log.d(TAG, "productsAPI: $it")
                navigator.hideProgressBar()
            }
        }
    }

}