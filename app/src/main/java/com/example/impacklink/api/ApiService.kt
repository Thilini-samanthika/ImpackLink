package com.example.impacklink.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("register.php")
    fun register(@Body request: RegisterRequest): Call<AuthResponse>

    @POST("login.php")
    fun login(@Body request: LoginRequest): Call<AuthResponse>
}