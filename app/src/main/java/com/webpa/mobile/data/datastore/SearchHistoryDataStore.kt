package com.webpa.mobile.data.datastore


import android.content.Context
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

private const val DATASTORE_NAME = "search_history"

val Context.searchHistoryDataStore by preferencesDataStore(
    name = DATASTORE_NAME
)

object SearchHistoryKeys {
    val QUERIES = stringSetPreferencesKey("queries")
}
