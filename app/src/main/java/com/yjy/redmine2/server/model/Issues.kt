package com.yjy.redmine2.server.model

data class Issues(
    val id: Int = 0,
    val updatedOn: String = "",
    val createdOn: String = "",
    val author: Author,
    val subject: String = "",
    val tracker: Tracker,
    val project: Project,
    val priority: Priority,
    val doneRatio: Int = 0,
    val status: Status,
    val startDate: String = ""
)