package com.webpa.mobile.presentation.products.details

import RatingRow
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.webpa.mobile.domain.model.Product
import com.webpa.mobile.presentation.components.productcard.MarketplaceBadge
import coil.compose.SubcomposeAsyncImage
import com.webpa.mobile.presentation.components.AppTopBar

@Composable
fun ProductDetailsScreen(
    onBackClick: () -> Unit,
    viewModel: ProductDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            ProductDetailsTopBar(onBackClick)
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (uiState) {
                is ProductDetailsUiState.Loading -> {
                    LoadingState()
                }
                is ProductDetailsUiState.Error -> {
                    ErrorState()
                }
                is ProductDetailsUiState.Success -> {
                    val product = (uiState as ProductDetailsUiState.Success).product
                    ProductDetailsContent(product)
                }
            }
        }
    }
}
@Composable
private fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Ошибка загрузки",
            color = MaterialTheme.colorScheme.error
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductDetailsTopBar(
    onBackClick: () -> Unit
) {
    AppTopBar(
        title = "Товар",
        canNavigateBack = true,
        onBackClick = onBackClick
    )
}

@Composable
private fun ProductDetailsContent(
    product: Product
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        ProductImage(product.imageUrl)

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ProductTitle(product.name)

            Spacer(Modifier.height(8.dp))

            ProductPrice(product.price)

            Spacer(Modifier.height(8.dp))

            RatingRow(
                rating = product.rating,
                feedbacks = product.feedbacks
            )

            Spacer(Modifier.height(12.dp))

            MarketplaceBadge(product.marketplace)

            Spacer(Modifier.height(16.dp))

            MarketplaceButton(
                marketplace = product.marketplace,
                url = product.url
            )


            Spacer(Modifier.height(16.dp))

            SellerInfo(
                seller = product.seller,
                sellerRating = product.sellerRating
            )

            if (!product.description.isNullOrBlank()) {
                Spacer(Modifier.height(16.dp))
                ProductDescription(product.description)
            }
        }
    }
}

@Composable
private fun ProductImage(
    imageUrl: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
    ) {
        SubcomposeAsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize(),
            loading = {
                ImagePlaceholder()
            },
            error = {
                ImagePlaceholder()
            }
        )
    }
}


@Composable
private fun ImagePlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(48.dp)
        )
    }
}



@Composable
private fun ProductTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
private fun ProductPrice(price: Float) {
    Text(
        text = "$price ₽",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun SellerInfo(
    seller: String,
    sellerRating: Float
) {
    Column {
        Text(
            text = "Продавец: $seller",
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "Рейтинг продавца: $sellerRating",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ProductDescription(description: String) {
    Column {
        Text(
            text = "Описание",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
@Composable
private fun MarketplaceButton(
    marketplace: String,
    url: String
) {
    val context = LocalContext.current

    if (marketplace.lowercase() == "wildberries") {
        Button(
            onClick = { openWildberries(context, url) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9E1C9E)
            )
        ) {
            Text(
                text = "Открыть в Wildberries",
                color = Color.White
            )
        }
    }
}


private fun openWildberries(
    context: Context,
    url: String
) {
    if (url.isBlank()) return

    val uri = Uri.parse(url)

    val intent = Intent(Intent.ACTION_VIEW, uri).apply {
        // Пытаемся открыть именно приложение WB
        setPackage("com.wildberries.ru")
    }

    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        // Если WB не установлен — fallback в браузер
        openInBrowser(context, uri)
    }
}
private fun openInBrowser(
    context: Context,
    uri: Uri
) {
    val browserIntent = Intent(Intent.ACTION_VIEW, uri)

    try {
        context.startActivity(
            Intent.createChooser(browserIntent, "Открыть в браузере")
        )
    } catch (e: Exception) {
        Log.e("OPEN_URL", "Cannot open url: $uri", e)
    }
}



