package com.example.storeapp.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @Expose
    @SerializedName("id")
    var id: Int,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("price")
    val price: Double,
    @Expose
    @SerializedName("description")
    val description: String,
    @Expose
    @SerializedName("category")
    val category: String,
    @Expose
    @SerializedName("image")
    val image: String,
    @Expose
    @SerializedName("rating")
    var rating: Rating
)

data class Rating(
    @Expose
    @SerializedName("rate")
    val rate: Double,
    @Expose
    @SerializedName("count")
    var count: Int
)
