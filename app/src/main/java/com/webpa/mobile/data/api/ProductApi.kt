package com.webpa.mobile.data.api

import com.webpa.mobile.data.dto.product.MainProductsResponse
import com.webpa.mobile.data.dto.product.ProductDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductApi{
    @POST("api/parser/search")
    suspend fun search(@Query("query") query: String) : Response<MainProductsResponse>


    @GET("api/products/fetchAll")
    suspend fun fetchAll() : Response<List<ProductDto>>
}