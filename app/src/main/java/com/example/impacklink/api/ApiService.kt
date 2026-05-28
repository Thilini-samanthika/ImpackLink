package com.example.impacklink.api

import model.DashboardResponse
import model.Project
import model.ProjectResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("register.php")
    fun register(@Body request: RegisterRequest): Call<AuthResponse>

    @POST("login.php")
    fun login(@Body request: LoginRequest): Call<AuthResponse>

    @GET("getProjects.php")
    fun getProjects(@Query("user_id") userId: Int): Call<ProjectResponse>

    @POST("createProject.php")
    fun createProject(@Body request: Project): Call<AuthResponse>

    @POST("updateProject.php")
    fun updateProject(@Body request: Project): Call<AuthResponse>

    @POST("deleteProject.php")
    fun deleteProject(@Body request: Map<String, Int>): Call<AuthResponse>

    @GET("dashboardStats.php")
    fun dashboardStats(): Call<DashboardResponse>
}
