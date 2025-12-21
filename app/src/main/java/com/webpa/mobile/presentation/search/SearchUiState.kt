package com.webpa.mobile.presentation.search

import com.webpa.mobile.domain.model.Product

sealed class SearchUiState {
    data class Success( val products: List<Product>): SearchUiState()
    data class Error(val message: String): SearchUiState()
    object Idle : SearchUiState()
    object Loading : SearchUiState()
}