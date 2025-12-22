package com.webpa.mobile.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.webpa.mobile.domain.model.SignInData
import com.webpa.mobile.domain.model.SignUpData
import com.webpa.mobile.domain.usecase.user.SignInUseCase
import com.webpa.mobile.domain.usecase.user.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState = _uiState.asStateFlow()

    fun signIn(username: String, password: String) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            try {
                signInUseCase(SignInData(username, password))
                _uiState.value = AuthUiState.Authorized
            } catch (e: Exception) {
                _uiState.value = AuthUiState.Error(e.message ?: "Ошибка входа")
            }
        }
    }

    fun signUp(
        username: String,
        email: String,
        phone: String,
        password: String
    ) {
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            try {
                val user = signUpUseCase(SignUpData(username, email, phone, password))
                _uiState.value = AuthUiState.Registered
            } catch (e: Exception) {
                _uiState.value = AuthUiState.Error(e.message ?: "Ошибка регистрации")
            }
        }
    }
}
