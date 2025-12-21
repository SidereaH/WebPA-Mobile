package com.webpa.mobile.domain.usecase.user

import com.webpa.mobile.domain.model.User
import com.webpa.mobile.domain.repository.UserRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(private val userRepository: UserRepository){
    suspend operator fun invoke(id: Long): User {
        return userRepository.getUserById(id)
    }
}