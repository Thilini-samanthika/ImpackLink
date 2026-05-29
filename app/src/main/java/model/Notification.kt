package model

data class Notification(
    val id: Int,
    val user_id: Int,
    val message: String,
    val created_at: String
)