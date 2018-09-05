package com.yjy.redmine2.repository.model

data class IssueInList(
    val id: Int = -1,
    val subject: String = "",
    val projectName: String = "",
    val assignTo: String = "",
    val statusName: String = "",
    val authorName: String = "",
    val priorityName: String = ""
)
