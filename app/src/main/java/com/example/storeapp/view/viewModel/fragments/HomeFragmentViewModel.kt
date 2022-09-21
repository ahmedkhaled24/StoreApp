package com.example.storeapp.view.viewModel.fragments

import android.content.Context
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.example.storeapp.view.Navigators.HomeNavigator
import com.example.storeapp.view.BaseComponents.BaseViewModel

private const val TAG = "HomeFragmentViewModel"

class HomeFragmentViewModel(
    activity: FragmentActivity,
    private var viewLifecycleOwner: LifecycleOwner,
    private var navigator: HomeNavigator
): BaseViewModel(activity) {

    fun productsAPI(context: Context) {
        navigator.showProgressBar()
        mUserViewModel.productsViewM().observe(viewLifecycleOwner) { dataResource ->
            // handle success
            dataResource.data?.getContentIfNotHandled()?.let {
                navigator.onProductsResponse(it)
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