package com.waffle.vascommtest.api

import com.waffle.vascommtest.data.request.AuthRequest
import com.waffle.vascommtest.data.response.LoginResponse
import com.waffle.vascommtest.data.response.RegisterResponse
import com.waffle.vascommtest.data.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("register")
    suspend fun postRegister(
        @Body authRequest: AuthRequest
    ) : Response<RegisterResponse>

    @POST("login")
    suspend fun postLogin(
        @Body authRequest: AuthRequest
    ) : Response<LoginResponse>

    @GET("user/{id}")
    suspend fun getUserData(
        @Path("id") id: Int
    ) : Response<UserResponse>

}