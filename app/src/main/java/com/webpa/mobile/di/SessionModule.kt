package com.webpa.mobile.di

import com.webpa.mobile.data.datastore.SessionStoreImpl
import com.webpa.mobile.domain.datastore.SessionStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SessionModule {

    @Binds
    @Singleton
    abstract fun bindSessionStore(
        impl: SessionStoreImpl
    ): SessionStore
}
