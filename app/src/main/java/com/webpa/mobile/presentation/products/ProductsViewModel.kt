package com.webpa.mobile.presentation.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.webpa.mobile.domain.model.Product
import com.webpa.mobile.domain.usecase.products.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
) : ViewModel(){
    private val _uiState = MutableStateFlow<ProductsUiState>(ProductsUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun onAllProductRequested(){
        viewModelScope.launch {
            _uiState.value = ProductsUiState.Loading
            val products : List<Product>
            try{
                products = getAllProductsUseCase.invoke()
                _uiState.value = ProductsUiState.Success(products)
            } catch (e : Exception){
                _uiState.value = ProductsUiState.Error( message = e.message ?: "Unknown error")
            }
        }
    }
}