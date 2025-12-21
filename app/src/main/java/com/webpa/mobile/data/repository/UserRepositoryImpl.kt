package com.webpa.mobile.data.repository

import com.webpa.mobile.data.api.UserApi
import com.webpa.mobile.data.exceptions.AuthException
import com.webpa.mobile.data.mapper.toCredentials
import com.webpa.mobile.data.mapper.toSignInRequest
import com.webpa.mobile.data.mapper.toSignUpRequest
import com.webpa.mobile.data.mapper.toUser
import com.webpa.mobile.data.security.JwtParser
import com.webpa.mobile.domain.datastore.SessionStore
import com.webpa.mobile.domain.model.AuthSession
import com.webpa.mobile.domain.model.SignInData
import com.webpa.mobile.domain.model.SignUpData
import com.webpa.mobile.domain.model.User
import com.webpa.mobile.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userApi: UserApi,
    private val jwtParser: JwtParser,
    private val sessionStore: SessionStore
): UserRepository {



    override suspend fun signUp(signUpData: SignUpData): User {

        val response = userApi.signUp(toSignUpRequest(signUpData))
        if (!response.isSuccessful) {
            throw AuthException("Ошибка регистрации" + response.message())
        }

        val body = response.body()
            ?: throw AuthException("Пустой ответ от сервера")

        return toUser(body)

    }

    override suspend fun updateRefreshToken(token: String): AuthSession {
        val response = userApi.refresh(token)
        if (!response.isSuccessful) {
            throw AuthException("Неверный токен" + response.message())
        }

        val body = response.body()
            ?: throw AuthException("Пустой ответ от сервера")

        return toCredentials(body)
    }

    override suspend fun logout(token: String) {
        userApi.logout(token)
    }

    override suspend fun getUserById(id: Long): User {
        val response = userApi.getUserById(id)
        if (!response.isSuccessful) {
            throw AuthException("Неверный токен" + response.message())
        }

        val body = response.body()
            ?: throw AuthException("Пустой ответ от сервера")

        return toUser(body)
    }

    override suspend fun signIn(data: SignInData): AuthSession {
        val response = userApi.signIn(toSignInRequest(data))
        val body = response.body() ?: error("Empty body")

        val userId = jwtParser.extractUserId(body.accessToken)

        sessionStore.save(
            AuthSession(
                accessToken = body.accessToken,
                refreshToken = body.refreshToken,
                userId = userId
            )
        )

        return AuthSession(body.accessToken, body.refreshToken, userId)
    }

}