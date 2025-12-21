package com.webpa.mobile.domain.usecase.favorites

import com.webpa.mobile.domain.model.FavoriteProduct
import com.webpa.mobile.domain.repository.FavoritesRepository
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {
    suspend operator fun invoke(product: FavoriteProduct)  {
        return favoritesRepository.addToFavorites(product)
    }
}