package com.webpa.mobile.domain.usecase.user

import com.webpa.mobile.domain.model.SignUpData
import com.webpa.mobile.domain.model.User
import com.webpa.mobile.domain.repository.UserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(signUpData: SignUpData): User {
        return userRepository.signUp(signUpData)
    }
}