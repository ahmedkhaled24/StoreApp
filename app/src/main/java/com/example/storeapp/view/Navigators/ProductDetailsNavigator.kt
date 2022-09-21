package com.example.storeapp.view.Navigators

import com.example.storeapp.model.ProductData
import com.example.storeapp.view.BaseComponents.BaseNavigator

interface ProductDetailsNavigator : BaseNavigator {
    fun onProductDetailsResponse(productData: ProductData)
}