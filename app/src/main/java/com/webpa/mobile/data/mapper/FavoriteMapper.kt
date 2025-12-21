package com.webpa.mobile.data.mapper

import com.webpa.mobile.data.entity.FavoriteEntity
import com.webpa.mobile.domain.model.FavoriteProduct

fun mapFromEntityToFavoriteProduct(
    favoriteEntity: FavoriteEntity) = FavoriteProduct(
    id = favoriteEntity.id,
    name = favoriteEntity.name,
    price = favoriteEntity.price,
    imageUrl = favoriteEntity.imageUrl,
    marketplace = favoriteEntity.marketplace
)
fun mapFavoriteProductToEntity(
    favoriteProduct: FavoriteProduct
) = FavoriteEntity(
    id = favoriteProduct.id,
    name = favoriteProduct.name,
    price = favoriteProduct.price,
    imageUrl = favoriteProduct.imageUrl,
    marketplace = favoriteProduct.marketplace
)
