package com.webpa.mobile.domain.usecase.products

import com.webpa.mobile.domain.repository.SearchHistoryRepository
import javax.inject.Inject

class SaveSearchQueryUseCase @Inject constructor(
    private val repository: SearchHistoryRepository
) {
    suspend operator fun invoke(query: String) {
        repository.saveQuery(query)
    }
}
