package com.lvsecoto.redmine2.server.model

data class IssueResponse(
    val issue: Issue = Issue()
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
        val spent_hours: Int = 0,
        val total_spent_hours: Int = 0,
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