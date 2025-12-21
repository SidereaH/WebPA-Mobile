package com.webpa.mobile.data.dto.product

data class MainProductsResponse (
    val success: Boolean,
    val message: String,
    val products: List<ProductDto>
    )