package com.yjy.redmine2.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "issues")
data class IssueEntity(
    @field:PrimaryKey
    val id : Int = -1,
    val subject : String = "",
    val projectId: Int = -1,
    val assignToId: Int = -1,
    val statusId : Int = -1,
    val authorId: Int = -1,
    val priorityId: Int = -1
)