package com.example.impacklink.api

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("mobile") val mobile: String?,
    @SerializedName("about") val about: String?,
    @SerializedName("role") val role: String?,
    @SerializedName("account_holder") val account_holder: String?,
    @SerializedName("account_number") val account_number: String?
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