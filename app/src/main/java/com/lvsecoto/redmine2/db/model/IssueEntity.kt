package com.lvsecoto.redmine2.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "issue")
data class IssueEntity(
    @field:PrimaryKey
    val issueId: Int = -1,
    val subject: String = "",
    val projectName: String = "",
    val assignTo: String = "",
    val authorName: String = "",
    val priorityName: String = "",
    val statusId: Int = -1
)