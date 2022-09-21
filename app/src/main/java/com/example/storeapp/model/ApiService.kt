package com.example.storeapp.model

import com.example.storeapp.model.response.ProductDetailsResponse
import com.example.storeapp.model.response.ProductsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    @GET("products")
    fun getProducts() : Single<List<ProductsResponse>>

    @GET("products/{id}")
    fun getProductDetails(@Path("id") id: Int) : Single<ProductDetailsResponse>
}