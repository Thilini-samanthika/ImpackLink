package com.example.impacklink.api

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val mobile: String?,
    val about: String?,
    val role: String?,
    val account_holder: String?,
    val account_number: String?
)

data class AuthResponse(
    val status: String = "error",
    val message: String = "",
    val user: User? = null
)

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val mobile: String?,
    val about: String?,
    val role: String?,
    val account_holder: String?,
    val account_number: String?
)

data class LoginRequest(
    val email: String,
    val password: String
)