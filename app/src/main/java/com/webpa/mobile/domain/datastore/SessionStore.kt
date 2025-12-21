package com.webpa.mobile.domain.datastore

import com.webpa.mobile.domain.model.AuthSession
import kotlinx.coroutines.flow.Flow

interface SessionStore {
    suspend fun save(session: AuthSession)
    suspend fun getUserId(): Long?
    suspend fun getAccessToken(): String?
    suspend fun getRefreshToken(): String?
    suspend fun clear()
    val isAuthorizedFlow: Flow<Boolean>
}

