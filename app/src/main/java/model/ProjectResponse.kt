package model

import com.google.gson.annotations.SerializedName

data class ProjectResponse(
    val status: String,
    @SerializedName("data")
    val projects: List<Project>
)