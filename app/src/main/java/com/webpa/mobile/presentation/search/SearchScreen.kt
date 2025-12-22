package com.webpa.mobile.presentation.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.webpa.mobile.domain.model.Product
import com.webpa.mobile.presentation.components.AppTopBar
import com.webpa.mobile.presentation.components.productcard.EmptySearchState
import com.webpa.mobile.presentation.components.productcard.ProductCard
import com.webpa.mobile.presentation.components.productcard.ProductsSkeletonList

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onProductClick: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsState()
    val query = viewModel.query.collectAsState()

    val history by viewModel.history.collectAsState()

    val favoriteIds by viewModel.favoriteIds.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        OutlinedTextField(
            value = query.value,
            onValueChange = viewModel::onQueryChanged,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Поиск товара") }
        )

        if (uiState is SearchUiState.Idle && history.isNotEmpty()) {
            Spacer(Modifier.height(12.dp))

            SearchHistoryList(
                history = history,
                onItemClick = { query ->
                    viewModel.onQueryChanged(query)
                    viewModel.onSearchRequested(query)
                }
            )
        }


        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { viewModel.onSearchRequested(query.value) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Поиск")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {
            is SearchUiState.Loading -> {
                ProductsSkeletonList()
            }

            is SearchUiState.Success -> {
                val products = (uiState as SearchUiState.Success).products

                if (products.isEmpty()) {
                    EmptySearchState(query.value)
                } else {
                    ProductList(
                        products = products,
                        favoriteIds = favoriteIds,
                        onFavoriteClick = viewModel::onFavoriteClick,
                        onProductClick = onProductClick
                    )
                }
            }

            is SearchUiState.Error -> {
                Text(
                    text = (uiState as SearchUiState.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
            }

            is SearchUiState.Idle -> {
                Text("Введите запрос для поиска")
            }
        }
    }
}
@Composable
private fun ProductList(
    products: List<Product>,
    favoriteIds: Set<Long>,
    onFavoriteClick: (Product) -> Unit,
    onProductClick: (Long) -> Unit
) {
    LazyColumn {
        items(products) { product ->
            AnimatedVisibility(
                visible = true,
                enter = fadeIn() + slideInVertically()
            ){
                ProductCard(
                    product = product,
                    isFavorite = favoriteIds.contains(product.id),
                    onFavoriteClick = {
                        onFavoriteClick(product)
                    },
                    onClick = {
                        onProductClick(product.id)
                    }
                )
            }


        }
    }
}
