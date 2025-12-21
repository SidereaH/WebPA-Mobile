package com.webpa.mobile.data.dto.user

data class SignupRequest (
    val username: String,
    val email: String,
    val phone: String,
    val password: String
)