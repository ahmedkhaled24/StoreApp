package com.example.storeapp.view.viewModel.fragments

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import com.example.storeapp.view.BaseComponents.BaseViewModel
import com.example.storeapp.view.Navigators.HomeNavigator

private const val TAG = "ProductDetailsViewModel"

class ProductDetailsViewModel (
    activity: FragmentActivity,
    private var viewLifecycleOwner: LifecycleOwner,
    private var navigator: HomeNavigator
) : BaseViewModel(activity) {


}