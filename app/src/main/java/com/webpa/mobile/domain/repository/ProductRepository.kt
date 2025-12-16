package com.webpa.mobile.domain.repository

import com.webpa.mobile.domain.model.Product


interface ProductRepository {
    suspend fun searchProducts(query: String) : List<Product>
    suspend fun getAllProducts() : List<Product>
}