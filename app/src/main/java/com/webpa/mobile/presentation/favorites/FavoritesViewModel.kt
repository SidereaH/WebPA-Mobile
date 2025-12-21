package com.webpa.mobile.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.webpa.mobile.domain.model.FavoriteProduct
import com.webpa.mobile.domain.usecase.favorites.ObserveFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    observeFavoritesUseCase: ObserveFavoritesUseCase
) : ViewModel() {

    val favorites: StateFlow<List<FavoriteProduct>> =
        observeFavoritesUseCase()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )
}
