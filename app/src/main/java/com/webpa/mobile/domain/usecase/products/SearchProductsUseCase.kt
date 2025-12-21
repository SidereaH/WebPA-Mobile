package com.webpa.mobile.domain.usecase.products

import com.webpa.mobile.domain.model.Product
import com.webpa.mobile.domain.repository.ProductRepository
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend operator fun invoke(query: String): List<Product> {
        return productRepository.searchProducts(query)
    }
}