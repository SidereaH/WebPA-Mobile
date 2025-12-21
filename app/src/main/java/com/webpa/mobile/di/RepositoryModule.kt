package com.webpa.mobile.di

import com.webpa.mobile.data.repository.FavoritesRepositoryImpl
import com.webpa.mobile.data.repository.ProductRepositoryImpl
import com.webpa.mobile.data.repository.SearchHistoryRepositoryImpl
import com.webpa.mobile.data.repository.UserRepositoryImpl
import com.webpa.mobile.domain.repository.FavoritesRepository
import com.webpa.mobile.domain.repository.ProductRepository
import com.webpa.mobile.domain.repository.SearchHistoryRepository
import com.webpa.mobile.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        impl: ProductRepositoryImpl
    ): ProductRepository
}
@Module
@InstallIn(SingletonComponent::class)
object SearchHistoryModule {

    @Provides
    @Singleton
    fun provideSearchHistoryRepository(
        impl: SearchHistoryRepositoryImpl
    ): SearchHistoryRepository = impl
}
@Module
@InstallIn(SingletonComponent::class)
object FavoritesProductModule {
    @Provides
    @Singleton
    fun provideFavoritesRepository(
        impl: FavoritesRepositoryImpl
    ): FavoritesRepository = impl
}

@Module
@InstallIn(SingletonComponent::class)
object UserModule{
    @Provides
    @Singleton
    fun provideUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository = impl
}