package com.lvsecoto.redmine2.server.model

data class StatusesResponse(
    val issue_statuses: List<IssueStatuses> = listOf()
) {

    data class IssueStatuses(
        val id: Int = 0,
        val name: String = "",
        val is_closed: Boolean = false
    )
}