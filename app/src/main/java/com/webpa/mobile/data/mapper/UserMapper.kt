package com.webpa.mobile.data.mapper

import com.webpa.mobile.data.dto.user.SignInRequest
import com.webpa.mobile.data.dto.user.SignupRequest
import com.webpa.mobile.data.dto.user.UserDto
import com.webpa.mobile.domain.model.SignInData
import com.webpa.mobile.domain.model.SignUpData
import com.webpa.mobile.domain.model.User
import kotlin.math.sign

fun toUser(userDto: UserDto) = User(
    id = userDto.id,
    username = userDto.username,
    email = userDto.email,
    phone = userDto.phone
)
fun toSignInRequest(signInData: SignInData) = SignInRequest(
    username = signInData.username,
    password = signInData.password
)
fun toSignUpRequest(signUpData: SignUpData) = SignupRequest(
    username = signUpData.username,
    email = signUpData.email,
    phone = signUpData.phone,
    password = signUpData.password
)