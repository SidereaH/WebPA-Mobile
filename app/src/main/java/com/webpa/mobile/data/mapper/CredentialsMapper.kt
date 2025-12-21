package com.webpa.mobile.data.mapper

import com.webpa.mobile.data.dto.user.AuthResponse
import com.webpa.mobile.domain.model.AuthSession

fun toCredentials(response: AuthResponse) = AuthSession(
    accessToken = response.accessToken,
    refreshToken = response.refreshToken,
    userId = 0
)