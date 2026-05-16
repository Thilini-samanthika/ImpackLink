package com.example.impacklink

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile_table")
data class UserProfile(
    @PrimaryKey val id: Int = 1, // අපිට තියෙන්නේ එක profile එකක් නිසා හැමතිස්සෙම id එක 1 වෙනවා
    val name: String,
    val email: String,
    val mobile: String,
    val about: String,
    val role: String,
    val accountHolderName: String,
    val accountNumber: String
)