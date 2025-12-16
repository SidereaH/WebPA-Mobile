package com.webpa.mobile.data.dto

import java.util.Date

data class ProductDto (
    val id: Float,
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
