package com.webpa.mobile.domain.usecase.favorites

import com.webpa.mobile.domain.model.FavoriteProduct
import com.webpa.mobile.domain.repository.FavoritesRepository
import javax.inject.Inject

class RemoveFromFavoritesUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {
    suspend operator fun invoke(productId: Long)  {
        return favoritesRepository.removeFromFavorites(productId)
    }
}