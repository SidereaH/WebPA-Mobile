package com.webpa.mobile.data.repository

import com.webpa.mobile.data.dao.FavoritesDao
import com.webpa.mobile.data.mapper.mapFavoriteProductToEntity
import com.webpa.mobile.data.mapper.mapFromEntityToFavoriteProduct
import com.webpa.mobile.domain.model.FavoriteProduct
import com.webpa.mobile.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(private val favoritesDao: FavoritesDao) : FavoritesRepository {
    override fun observeFavorites(): Flow<List<FavoriteProduct>> {
        return favoritesDao.observeFavorites()
            .map { entityList ->
                entityList.map { entity ->
                    mapFromEntityToFavoriteProduct(entity)
                }
            }
    }


    override suspend fun addToFavorites(product: FavoriteProduct) {
        val entity = mapFavoriteProductToEntity(product)
        favoritesDao.insert(entity)
    }

    override suspend fun removeFromFavorites(productId: Long) {
        favoritesDao.delete(productId)
    }

    override suspend fun isFavorite(productId: Long): Boolean {
        return favoritesDao.isFavorite(productId)
    }
}