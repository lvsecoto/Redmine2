package com.yjy.redmine2.server.model


data class UpdateIssueStatusRequest(
    val issue: Issue = Issue()
) {

    data class Issue(
        val status_id: Int = 3
    )
}