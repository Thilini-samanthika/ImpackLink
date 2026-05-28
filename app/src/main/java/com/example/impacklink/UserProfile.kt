package com.example.impacklink

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile_table")
data class UserProfile(
    @PrimaryKey val id: Int = 1,
    val name: String,
    val email: String,
    val mobile: String,
    val about: String, // mapped to Description
    val role: String,
    val accountHolderName: String,
    val accountNumber: String,
    val location: String = "",
    val website: String = "",
    val ngoId: String = "",
    val positionName: String = "",
    val profileImageUri: String? = null
)