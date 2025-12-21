package com.webpa.mobile.domain.model

data class AuthSession (
    val accessToken: String,
    val refreshToken: String,
    val userId: Long
)