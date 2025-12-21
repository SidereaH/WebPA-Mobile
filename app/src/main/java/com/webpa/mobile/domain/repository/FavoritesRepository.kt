package com.webpa.mobile.domain.repository

import com.webpa.mobile.domain.model.FavoriteProduct
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun observeFavorites(): Flow<List<FavoriteProduct>>

    suspend fun addToFavorites(product: FavoriteProduct)

    suspend fun removeFromFavorites(productId: Long)

    suspend fun isFavorite(productId: Long): Boolean
}
