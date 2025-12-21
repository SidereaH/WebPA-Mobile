package com.webpa.mobile.domain.usecase.favorites

import com.webpa.mobile.domain.repository.FavoritesRepository
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {
    suspend operator fun invoke(productId: Long) : Boolean  {
        return favoritesRepository.isFavorite(productId)
    }
}