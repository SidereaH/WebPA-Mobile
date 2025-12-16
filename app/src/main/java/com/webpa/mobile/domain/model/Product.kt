package com.webpa.mobile.domain.model

data class Product (
    val name: String,
    val description: String?,
    val price: Float,
    val rating: Float,
    val brand: String,
    val seller: String,
    val sellerRating: Float,
    val marketplace: String,
    val url: String,
    val imageUrl: String
)