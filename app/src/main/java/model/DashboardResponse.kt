package model

data class DashboardResponse(
    val status: String,
    val users: Int,
    val projects: Int,
)