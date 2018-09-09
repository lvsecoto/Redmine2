package com.yjy.redmine2.server.model


data class UpdateIssueStatusRequest(
    val issue: Issue
) {
    constructor(status_id: Int) : this(
        Issue(status_id)
    )

    data class Issue(
        val status_id: Int
    )
}