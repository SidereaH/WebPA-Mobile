package com.webpa.mobile.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.webpa.mobile.domain.datastore.SessionStore
import com.webpa.mobile.domain.model.User
import com.webpa.mobile.domain.usecase.user.GetUserByIdUseCase
import com.webpa.mobile.domain.usecase.user.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val sessionStore: SessionStore
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)

    val uiState: StateFlow<ProfileUiState> = _uiState

    init {
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            try {
                val userId = sessionStore.getUserId()
                if (userId != null){
                    val user = getUserByIdUseCase(userId)
                    _uiState.value = ProfileUiState.Success(user)
                }
                else{
                    throw Exception("Отсутствует UserId")
                }
            } catch (e: Exception) {
                _uiState.value = ProfileUiState.Error(
                    e.message ?: "Ошибка загрузки профиля"
                )
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            sessionStore.clear()
        }
    }
}
