package com.webpa.mobile.presentation.components.productcard

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable

@Composable
fun ProductsSkeletonList() {
    LazyColumn {
        items(6) {
            ProductCardSkeleton()
        }
    }
}
