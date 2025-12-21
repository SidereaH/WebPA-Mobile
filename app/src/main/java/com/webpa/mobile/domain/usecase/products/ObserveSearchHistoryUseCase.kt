package com.webpa.mobile.domain.usecase.products

import com.webpa.mobile.domain.model.SearchHistoryItem
import com.webpa.mobile.domain.repository.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveSearchHistoryUseCase @Inject constructor(
    private val repository: SearchHistoryRepository
) {
    operator fun invoke(): Flow<List<SearchHistoryItem>> =
        repository.observeHistory()
}