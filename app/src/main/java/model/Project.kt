package model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Project(
    @SerializedName("id") val id: Int,
    @SerializedName("user_id") val user_id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("budget") val budget: Double,
    @SerializedName("status") val status: String,
    @SerializedName("required_volunteers") val required_volunteers: Int = 0
) : Serializable {
    companion object {
        val projectList = mutableListOf<Project>(
            Project(1, 1, "Project A-NGO X", "Description A", 1000.0, "Active", 10),
            Project(2, 1, "Project B-NGO Y", "Description B", 2000.0, "Active", 20)
        )
    }
}