package com.webpa.mobile.di

import com.webpa.mobile.data.api.ProductApi
import com.webpa.mobile.data.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{
    @Provides
    fun providesBaseUrl() : String = "http://10.0.2.2:8080/"

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL : String) : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideProductApi(retrofit : Retrofit) : ProductApi = retrofit.create(ProductApi::class.java)

    @Provides
    @Singleton
    fun provideUserApi(retrofit : Retrofit) : UserApi = retrofit.create(UserApi::class.java)

}