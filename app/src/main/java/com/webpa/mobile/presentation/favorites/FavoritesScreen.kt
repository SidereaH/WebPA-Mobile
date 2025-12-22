package com.webpa.mobile.presentation.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.webpa.mobile.domain.model.FavoriteProduct
import com.webpa.mobile.presentation.components.favorites.EmptyFavoritesState
import com.webpa.mobile.presentation.components.favorites.FavoriteProductCard


@Composable
fun FavoritesScreen(
    onProductClick: (Long) -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val favorites by viewModel.favorites.collectAsState()

    when {
        favorites.isEmpty() -> {
            EmptyFavoritesState()
        }
        else -> {
            FavoritesList(
                favorites = favorites,
                onProductClick = onProductClick,
                modifier
            )
        }
    }
}

@Composable
private fun FavoritesList(
    favorites: List<FavoriteProduct>,
    onProductClick: (Long) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(horizontal = 16.dp, vertical = 20.dp)

    ) {
        items(favorites) { product ->
            FavoriteProductCard(
                product = product,
                onClick = { onProductClick(product.id) }
            )
        }
    }
}




