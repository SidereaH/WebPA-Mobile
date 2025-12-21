package com.webpa.mobile.presentation.products.details

import com.webpa.mobile.domain.model.Product

sealed class ProductDetailsUiState {
    object Loading : ProductDetailsUiState()
    data class Success(val product: Product) : ProductDetailsUiState()
    data class Error(val message: String) : ProductDetailsUiState()
}
