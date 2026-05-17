package com.example.impacklink

data class NotificationModel(
    val id: String,
    val title: String,
    val description: String,
    val type: String,
    val statusText: String,
    val iconType: String
)