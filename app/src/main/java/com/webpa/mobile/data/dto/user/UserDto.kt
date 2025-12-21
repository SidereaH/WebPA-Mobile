package com.webpa.mobile.data.dto.user

data class UserDto (
    val id: Long,
    val username: String,
    val email: String,
    val password: String,
    val phone: String,
    val role: String,
    val authorities: List<AuthorityDto>,
    val accountNonExpired: Boolean,
    val accountNonLocked: Boolean,
    val credentialsNonExpired: Boolean,
    val enabled: Boolean
)