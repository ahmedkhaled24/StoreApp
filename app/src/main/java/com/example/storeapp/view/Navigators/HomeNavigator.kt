package com.example.storeapp.view.Navigators

import com.example.storeapp.model.custom.ProductData
import com.example.storeapp.view.BaseComponents.BaseNavigator


interface HomeNavigator: BaseNavigator {
    fun onProductsResponse(response: MutableList<ProductData>)
}