package com.webpa.mobile.domain.model

data class Product (
    val id: Long,
    val name: String,
    val description: String?,
    val price: Float,
    val rating: Float,
    val feedbacks: Int,
    val brand: String,
    val seller: String,
    val sellerRating: Float,
    val marketplace: String,
    val url: String,
    val imageUrl: String
)
fun toFavorite(product: Product) = FavoriteProduct(
    id = product.id,
    name = product.name,
    price = product.price,
    imageUrl = product.imageUrl,
    marketplace = product.marketplace
)