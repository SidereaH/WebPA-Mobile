package com.webpa.mobile.domain.usecase.favorites

import com.webpa.mobile.domain.model.FavoriteProduct
import com.webpa.mobile.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveFavoritesUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {
    operator fun invoke(): Flow<List<FavoriteProduct>> {
        return favoritesRepository.observeFavorites()
    }
}