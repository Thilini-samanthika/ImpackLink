package model

data class Application(
    val projectId: Int,
    val projectTitle: String,
    val volunteerName: String,
    var status: String = "Pending"
) {
    companion object {
        val applicationList = mutableListOf<Application>()
    }
}
