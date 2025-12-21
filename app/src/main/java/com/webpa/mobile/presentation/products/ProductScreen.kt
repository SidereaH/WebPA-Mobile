package com.webpa.mobile.presentation.products

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun ProductsScreen (
    viewModel: ProductsViewModel = hiltViewModel()
    ) {

    val uiState = viewModel.uiState.collectAsState()


}