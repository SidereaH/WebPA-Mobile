package com.webpa.mobile.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.webpa.mobile.domain.datastore.SessionStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    sessionStore: SessionStore
) : ViewModel() {

    val isAuthorized: StateFlow<Boolean> =
        sessionStore.isAuthorizedFlow
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = false
            )
}
