package com.webpa.mobile.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
@Composable
fun ProfileScreen(
    onLogout: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    when (state) {
        is ProfileUiState.Loading -> {
            CircularProgressIndicator()
        }

        is ProfileUiState.Error -> {
            Text("Ошибка загрузки профиля")
        }

        is ProfileUiState.NotAuthorized -> {
            Text("Вы не авторизованы")
        }

        is ProfileUiState.Success -> {
            val user = (state as ProfileUiState.Success).user

            Column {
                ProfileRow("Имя", user.username)
                ProfileRow("Email", user.email)
                ProfileRow("Телефон", user.phone)

                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = {
                        viewModel.logout()
                        onLogout()
                    }
                ) {
                    Text("Выйти")
                }
            }
        }
    }
}

@Composable
private fun ProfileRow(label: String, value: String) {
    Column {
        Text(label, style = MaterialTheme.typography.labelMedium)
        Text(value, style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(12.dp))
    }
}
