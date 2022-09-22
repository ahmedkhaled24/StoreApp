package com.example.storeapp.model.custom

data class ProductData(
    val id: Int,
    val image: String,
    val title: String,
    val description: String? = null,
    val price: Double,
    val rating: Double? = null
)
