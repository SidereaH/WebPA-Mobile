package com.webpa.mobile.presentation.components.productcard

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun MarketplaceBadge(marketplace: String) {

    val context = LocalContext.current

    val (label, color) = when (marketplace.lowercase()) {
        "wildberries" -> "Wildberries" to Color(0xFF9E1C9E)
        "ozon" -> "Ozon" to Color(0xFF005BFF)
        else -> marketplace to MaterialTheme.colorScheme.outline
    }

    Surface(
        color = color.copy(alpha = 0.12f),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall,
            color = color
        )
    }
}

