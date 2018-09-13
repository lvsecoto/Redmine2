package com.yjy.redmine2.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "attachment")
data class AttachmentEntity(
    @PrimaryKey
    val attachmentId: Int,
    val issueId: Int,
    val contentUrl: String,
    val fileName: String,
    val authorName: String
)