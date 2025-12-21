package com.webpa.mobile.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.webpa.mobile.data.datastore.SearchHistoryKeys
import com.webpa.mobile.data.datastore.searchHistoryDataStore
import com.webpa.mobile.domain.model.SearchHistoryItem
import com.webpa.mobile.domain.repository.SearchHistoryRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchHistoryRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : SearchHistoryRepository {

    private val dataStore = context.searchHistoryDataStore
    private val maxSize = 5

    override fun observeHistory(): Flow<List<SearchHistoryItem>> {
        return dataStore.data.map { prefs ->
            val queries = prefs[SearchHistoryKeys.QUERIES].orEmpty()

            queries
                .map { query ->
                    SearchHistoryItem(
                        query = query,
                        timestamp = 0L
                    )
                }
        }
    }

    override suspend fun saveQuery(query: String) {
        val normalized = query.trim()
        if (normalized.isBlank()) return

        dataStore.edit { prefs ->
            val current = prefs[SearchHistoryKeys.QUERIES].orEmpty()

            val updated = LinkedHashSet<String>()
            updated.add(normalized)
            updated.addAll(current)

            prefs[SearchHistoryKeys.QUERIES] =
                updated.take(maxSize).toSet()
        }
    }
}
