package com.webpa.mobile.domain.model

data class FavoriteProduct(
    val id: Long,
    val name: String,
    val price: Float,
    val imageUrl: String,
    val marketplace: String
)
