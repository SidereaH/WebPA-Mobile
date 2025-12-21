package com.webpa.mobile.data.dto.product


data class ProductDto (
    val id: Long,
    val marketplaceId: String,
    val name: String,
    val price: Float,
    val image: String,
    val mainInfo: MainInfoDto,
    val additionalInformation: AdditionalInfoDto,
    val description: String?,
    val marketplace: String,
    val url: String,
    val excelFilename: String,
    val createdAt: String,
    val updatedAt: String

)
