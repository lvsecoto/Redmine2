package com.yjy.redmine2.db.model

import androidx.room.Entity

@Entity(tableName = "attachment")
data class AttachmentEntity(
    val attachmentId: Int,
    val issueId: Int
)