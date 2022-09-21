package com.example.storeapp.model

import com.example.storeapp.model.response.ProductsResponse
import io.reactivex.Single
import retrofit2.http.GET


interface ApiService {

    @GET("products")
    fun getProducts() : Single<List<ProductsResponse>>
}