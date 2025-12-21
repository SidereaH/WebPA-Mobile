package com.webpa.mobile.domain.repository

import com.webpa.mobile.domain.model.AuthSession
import com.webpa.mobile.domain.model.SignInData
import com.webpa.mobile.domain.model.SignUpData
import com.webpa.mobile.domain.model.User

interface UserRepository {
    suspend fun signIn(signInData: SignInData): AuthSession

    suspend fun signUp(signUpData: SignUpData): User

    suspend fun updateRefreshToken(token: String): AuthSession

    suspend fun logout(token: String)

    suspend fun getUserById(id: Long): User


}