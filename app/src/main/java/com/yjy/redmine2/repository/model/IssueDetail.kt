package com.yjy.redmine2.repository.model

import androidx.room.Relation
import com.yjy.redmine2.db.model.AttachmentEntity

data class IssueDetail(
    val issueId: Int,
    val subject: String,
    val status: String? = "none"
) {

    @Relation(
        entity = AttachmentEntity::class,
        parentColumn = "issueId",
        entityColumn = "issueId",
        projection = ["contentUrl"]
    )
    var attachments = emptyList<Attachment>()

    companion object {
        data class Attachment(
            val contentUrl: String
        )
    }
}
