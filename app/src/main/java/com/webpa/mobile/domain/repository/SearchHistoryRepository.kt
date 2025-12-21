package com.webpa.mobile.domain.repository

import com.webpa.mobile.domain.model.SearchHistoryItem
import kotlinx.coroutines.flow.Flow

interface SearchHistoryRepository {
    suspend fun saveQuery(query: String)
    fun observeHistory(): Flow<List<SearchHistoryItem>>
}
