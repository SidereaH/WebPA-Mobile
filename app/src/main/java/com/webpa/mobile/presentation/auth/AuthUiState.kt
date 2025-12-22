package com.webpa.mobile.presentation.auth

sealed class AuthUiState {
    object Idle : AuthUiState()
    object Loading : AuthUiState()
    object Authorized : AuthUiState()
    data class Error(val message: String) : AuthUiState()
    object Registered : AuthUiState()
}
