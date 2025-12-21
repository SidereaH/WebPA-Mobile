package com.webpa.mobile.presentation.components.productcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ProductCardSkeleton(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )

            Spacer(Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                SkeletonLine(widthFraction = 0.9f)
                Spacer(Modifier.height(6.dp))
                SkeletonLine(widthFraction = 0.4f)
                Spacer(Modifier.height(12.dp))
                SkeletonLine(widthFraction = 0.6f)
            }
        }
    }
}

@Composable
private fun SkeletonLine(widthFraction: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth(widthFraction)
            .height(14.dp)
            .clip(MaterialTheme.shapes.small)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    )
}
