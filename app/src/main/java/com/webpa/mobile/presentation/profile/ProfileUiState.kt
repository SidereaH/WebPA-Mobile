package com.webpa.mobile.presentation.profile

import com.webpa.mobile.domain.model.Product
import com.webpa.mobile.domain.model.User
import com.webpa.mobile.presentation.search.SearchUiState

sealed class ProfileUiState {
    object Loading : ProfileUiState()
    object Error : ProfileUiState()
    object NotAuthorized : ProfileUiState()
    data class Success(val user: User) : ProfileUiState()
}
