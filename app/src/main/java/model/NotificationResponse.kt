package model

data class NotificationResponse(
    val status: String,
    val data: List<Notification>
)