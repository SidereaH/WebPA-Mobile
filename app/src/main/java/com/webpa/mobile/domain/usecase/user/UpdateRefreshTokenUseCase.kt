package com.webpa.mobile.domain.usecase.user

import com.webpa.mobile.domain.model.AuthSession
import com.webpa.mobile.domain.repository.UserRepository
import javax.inject.Inject

class UpdateRefreshTokenUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(refreshToken: String): AuthSession{
        return userRepository.updateRefreshToken(refreshToken)
    }
}