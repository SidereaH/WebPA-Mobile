package com.webpa.mobile.presentation.profile

import com.webpa.mobile.domain.model.Product
import com.webpa.mobile.domain.model.User
import com.webpa.mobile.presentation.search.SearchUiState

sealed class ProfileUiState {
    object Loading : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()
    data class Success(val user: User) : ProfileUiState()
}
