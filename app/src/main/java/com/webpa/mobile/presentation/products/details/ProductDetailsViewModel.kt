package com.webpa.mobile.presentation.products.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.webpa.mobile.domain.model.Product
import com.webpa.mobile.domain.model.toFavorite
import com.webpa.mobile.domain.usecase.products.GetAllProductsUseCase
import com.webpa.mobile.domain.usecase.products.RemoveFromFavoritesUseCase
import com.webpa.mobile.domain.usecase.favorites.AddToFavoritesUseCase
import com.webpa.mobile.domain.usecase.favorites.IsFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase
) : ViewModel() {

    private val productId: Long =
        savedStateHandle["productId"]
            ?: error("productId is required")

    private val _uiState =
        MutableStateFlow<ProductDetailsUiState>(ProductDetailsUiState.Loading)

    val uiState: StateFlow<ProductDetailsUiState> =
        _uiState.asStateFlow()


    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()


    private val currentProduct: Product?
        get() = (uiState.value as? ProductDetailsUiState.Success)?.product

    init {
        loadProduct()
    }

    private suspend fun loadFavoriteState(productId: Long) {
        _isFavorite.value = isFavoriteUseCase(productId)
    }
    private fun loadProduct() {
        viewModelScope.launch {
            try {
                val products = getAllProductsUseCase()
                val product = products.first { it.id == productId }

                _uiState.value = ProductDetailsUiState.Success(product)
                loadFavoriteState(product.id)
            } catch (e: Exception) {
                _uiState.value = ProductDetailsUiState.Error(
                    e.message ?: "Failed to load product"
                )
            }
        }
    }

    fun onFavoriteClick() {
        viewModelScope.launch {
            val product = currentProduct ?: return@launch

            if (_isFavorite.value) {
                removeFromFavoritesUseCase(product.id)
            } else {
                addToFavoritesUseCase(toFavorite(product))
            }

            _isFavorite.value = !_isFavorite.value
        }
    }
}



