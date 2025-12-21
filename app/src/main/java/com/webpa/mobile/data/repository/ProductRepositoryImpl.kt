package com.webpa.mobile.data.repository

import com.webpa.mobile.data.api.ProductApi
import com.webpa.mobile.data.mapper.mapProduct
import com.webpa.mobile.domain.model.Product
import com.webpa.mobile.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(private val productApi : ProductApi) : ProductRepository {
    override suspend fun searchProducts(query: String): List<Product> {
        val response = productApi.search(query)

        if (!response.isSuccessful) {
            throw RuntimeException("Network error: ${response.code()}")
        }

        val products = response.body()?.products
        return products?.map { dto -> mapProduct(dto) } ?: emptyList()
    }


    override suspend fun getAllProducts(): List<Product> {
        val response = productApi.fetchAll()
        if (!response.isSuccessful) {
            throw RuntimeException("Network error: ${response.code()}")
        }
        val body = response.body() ?: emptyList()
        return body.map { dto -> mapProduct(dto) }
    }
}
