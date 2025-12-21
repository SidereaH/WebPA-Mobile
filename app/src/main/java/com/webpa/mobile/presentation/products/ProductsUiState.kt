package com.webpa.mobile.presentation.products

import com.webpa.mobile.domain.model.Product

sealed class ProductsUiState {
    data class Success(val products: List<Product>) : ProductsUiState()
    data class Error(val message: String) : ProductsUiState()
    object Loading : ProductsUiState()
    object Idle : ProductsUiState()
}