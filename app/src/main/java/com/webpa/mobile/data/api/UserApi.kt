package com.webpa.mobile.data.api

import com.webpa.mobile.data.dto.user.AuthResponse
import com.webpa.mobile.data.dto.user.SignInRequest
import com.webpa.mobile.data.dto.user.SignupRequest
import com.webpa.mobile.data.dto.user.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {
    @POST("auth/signup")
    suspend fun signUp(@Body signUpRequest: SignupRequest) : Response<UserDto>

    @POST("auth/signin")
    suspend fun  signIn(@Body signInRequest: SignInRequest) : Response<AuthResponse>

    @POST("auth/refresh")
    suspend fun refresh(@Query("refreshToken") refreshToken: String) : Response<AuthResponse>

    @POST("auth/logout")
    suspend fun logout(@Query("refreshToken") refreshToken: String) : Response<String>

    @GET("api/users/{id}")
    suspend fun getUserById(
        @Path("id") id: Long
    ): Response<UserDto>
}