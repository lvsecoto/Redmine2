package com.yjy.redmine2.repository.model

data class IssueDetail(
    val issueId : Int,
    val subject: String,
    val status: String? = "none"
)
