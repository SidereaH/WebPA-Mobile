package com.webpa.mobile.data.mapper

import com.webpa.mobile.data.dto.product.ProductDto
import com.webpa.mobile.domain.model.Product

fun mapProduct(
    productDto: ProductDto,
) = Product(
    id = productDto.id,
    name = productDto.name,
    description = productDto.description,
    price = productDto.price,
    rating = productDto.mainInfo.rating,
    brand = productDto.mainInfo.brand,
    seller = productDto.additionalInformation.seller,
    sellerRating = productDto.additionalInformation.supplierRating,
    marketplace = productDto.marketplace,
    url = productDto.marketplace,
    imageUrl = productDto.image,
    feedbacks = productDto.mainInfo.feedbacks
)