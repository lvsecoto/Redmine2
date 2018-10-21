package com.lvsecoto.redmine2.repository.model

import androidx.room.Relation
import com.lvsecoto.redmine2.db.model.AttachmentEntity

data class IssueDetail(
    val issueId: Int,
    val subject: String,
    val status: String? = "none"
) {

    @Relation(
        entity = AttachmentEntity::class,
        parentColumn = "issueId",
        entityColumn = "issueId",
        projection = ["contentUrl", "fileName", "authorName"]
    )
    var attachments = emptyList<Attachment>()

    companion object {
        data class Attachment(
            val contentUrl: String,
            val fileName: String,
            val authorName: String
        )
    }
}
