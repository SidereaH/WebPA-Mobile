package com.webpa.mobile.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.webpa.mobile.domain.datastore.SessionStore
import com.webpa.mobile.domain.model.AuthSession
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class SessionStoreImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SessionStore {

    private val ACCESS = stringPreferencesKey("access")
    private val REFRESH = stringPreferencesKey("refresh")
    private val USER_ID = longPreferencesKey("user_id")

    override suspend fun save(session: AuthSession) {
        dataStore.edit {
            it[ACCESS] = session.accessToken
            it[REFRESH] = session.refreshToken
            it[USER_ID] = session.userId
        }
    }

    override suspend fun getUserId(): Long? =
        dataStore.data.first()[USER_ID]

    override suspend fun getAccessToken(): String? =
        dataStore.data.first()[ACCESS]

    override suspend fun getRefreshToken(): String? =
        dataStore.data.first()[REFRESH]

    override val isAuthorizedFlow: Flow<Boolean> =
        dataStore.data.map { prefs ->
            prefs[ACCESS] != null
        }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}

