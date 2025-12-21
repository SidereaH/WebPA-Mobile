package com.webpa.mobile.presentation.components.productcard

import RatingRow
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.webpa.mobile.domain.model.Product

@Composable
fun ProductCard(
    product: Product,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onClick: () -> Unit,
    modifier : Modifier = Modifier
){

    val scale by animateFloatAsState(
        targetValue = if (isFavorite) 1.3f else 1f,
        label = "favorite"
    )
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(12.dp)
        ) {

            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "${product.price} ₽",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text="Бренд: ${product.brand}"
                )

                Spacer(modifier = Modifier.height(6.dp))

                RatingRow(
                    rating = product.rating,
                    feedbacks = product.feedbacks
                )
                Spacer(modifier = Modifier.height(6.dp))

                MarketplaceBadge(marketplace = product.marketplace)
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.padding(2.dp)
                ){
                    Text(
                        modifier = Modifier.padding(vertical = 12.dp),
                        text = if (isFavorite)
                            "В избранном"
                        else
                            "Добавить в избранное"
                    )
                    IconButton(onClick = onFavoriteClick) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.Star,
                            contentDescription = null,
                            modifier = Modifier.scale(scale),
                            tint = if (isFavorite) Color(0xFFFFC107)
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }


            }
        }
    }
}


