package com.yjy.redmine2.server.model


data class AttachmentsResponse(
    val attachments: List<Attachment> = listOf()
) {

    data class Attachment(
        val id: Int = 0,
        val filename: String = "",
        val filesize: Int = 0,
        val content_type: String = "",
        val description: String = "",
        val content_url: String = "",
        val thumbnail_url: String = "",
        val author: Author = Author(),
        val created_on: String = ""
    ) {

        data class Author(
            val id: Int = 0,
            val name: String = ""
        )
    }
}