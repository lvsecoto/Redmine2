package com.yjy.redmine2.server.model


data class IssuesResponse(
    val issues: List<Issue> = listOf(),
    val total_count: Int = 0,
    val offset: Int = 0,
    val limit: Int = 0
) {

    data class Issue(
        val id: Int = 0,
        val project: Project = Project(),
        val tracker: Tracker = Tracker(),
        val status: Status = Status(),
        val priority: Priority = Priority(),
        val author: Author = Author(),
        val assigned_to: AssignedTo = AssignedTo(),
        val subject: String = "",
        val description: String = "",
        val start_date: String = "",
        val done_ratio: Int = 0,
        val created_on: String = "",
        val updated_on: String = ""
    ) {

        data class Status(
            val id: Int = 0,
            val name: String = ""
        )


        data class AssignedTo(
            val id: Int = 0,
            val name: String = ""
        )


        data class Author(
            val id: Int = 0,
            val name: String = ""
        )


        data class Project(
            val id: Int = 0,
            val name: String = ""
        )


        data class Priority(
            val id: Int = 0,
            val name: String = ""
        )


        data class Tracker(
            val id: Int = 0,
            val name: String = ""
        )
    }
}