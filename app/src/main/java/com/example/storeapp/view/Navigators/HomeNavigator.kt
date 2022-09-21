package com.example.storeapp.view.Navigators

import com.example.storeapp.model.response.ProductsResponse
import com.example.storeapp.view.BaseComponents.BaseNavigator


interface HomeNavigator: BaseNavigator {
    fun onProductsResponse(response: List<ProductsResponse>)
}