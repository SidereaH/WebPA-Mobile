package com.webpa.mobile.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.webpa.mobile.domain.model.Product
import com.webpa.mobile.domain.model.SearchHistoryItem
import com.webpa.mobile.domain.model.toFavorite
import com.webpa.mobile.domain.usecase.products.ObserveSearchHistoryUseCase
import com.webpa.mobile.domain.usecase.products.RemoveFromFavoritesUseCase
import com.webpa.mobile.domain.usecase.products.SaveSearchQueryUseCase
import com.webpa.mobile.domain.usecase.products.SearchProductsUseCase
import com.webpa.mobile.domain.usecase.favorites.AddToFavoritesUseCase
import com.webpa.mobile.domain.usecase.favorites.ObserveFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchProductsUseCase: SearchProductsUseCase,
    private val observeSearchHistoryUseCase: ObserveSearchHistoryUseCase,
    private val saveSearchQueryUseCase: SaveSearchQueryUseCase,
    private val observeFavoritesUseCase: ObserveFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()
    private val _favoriteIds = MutableStateFlow<Set<Long>>(emptySet())
    val favoriteIds: StateFlow<Set<Long>> = _favoriteIds
    init {
        observeFavorites()
    }
    private val _history =
        MutableStateFlow<List<SearchHistoryItem>>(emptyList())

    val history: StateFlow<List<SearchHistoryItem>> =
        _history.asStateFlow()

    init {
        observeSearchHistory()
    }

    private fun observeSearchHistory() {
        viewModelScope.launch {
            observeSearchHistoryUseCase()
                .collect { items ->
                    _history.value = items
                }
        }
    }

    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
    }
    fun onSearchRequested(query: String) {
        viewModelScope.launch {
            saveSearchQueryUseCase(query)

            _uiState.value = SearchUiState.Loading

            try {
                val products = searchProductsUseCase(query)
                _uiState.value = SearchUiState.Success(products)
            } catch (e: Exception) {
                _uiState.value = SearchUiState.Error(
                    e.message ?: "Unknown error"
                )
            }
        }
    }
    private fun observeFavorites() {
        viewModelScope.launch {
            observeFavoritesUseCase()
                .collect { favorites ->
                    _favoriteIds.value = favorites.map { it.id }.toSet()
                }
        }
    }

    fun onFavoriteClick(product: Product) {
        viewModelScope.launch {
            if (favoriteIds.value.contains(product.id)) {
                removeFromFavoritesUseCase(product.id)
            } else {
                addToFavoritesUseCase(toFavorite(product))
            }
        }
    }

}
