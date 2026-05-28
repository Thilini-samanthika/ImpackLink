package model

import java.io.Serializable

data class Project(
    val id: Int,
    val user_id: Int,
    val title: String,
    val description: String,
    val budget: Double,
    val status: String
) : Serializable {
    companion object {
        val projectList = mutableListOf<Project>(
            Project(1, 1, "Project A-NGO X", "Description A", 1000.0, "Active"),
            Project(2, 1, "Project B-NGO Y", "Description B", 2000.0, "Active")
        )
    }
}