package com.webpa.mobile.presentation.components.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.webpa.mobile.domain.model.FavoriteProduct

@Composable
fun FavoriteProductCard(
    product: FavoriteProduct,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = product.name,
                modifier = Modifier.weight(1f)
            )
            Text("${product.price} ₽")
        }
    }
}
