package com.yjy.redmine2.server

import androidx.lifecycle.LiveData
import com.yjy.redmine2.common.ApiResponse
import com.yjy.redmine2.server.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface Server {

    @GET("issues.json?assigned_to_id=me")
    fun getIssues(): LiveData<ApiResponse<IssuesResponse>>

    @GET("issues/{issueId}.json")
    fun getIssue(@Path("issueId") issueId: Int) : LiveData<ApiResponse<IssueResponse>>

    @GET("issue_statuses.json")
    fun getStatuses(): LiveData<ApiResponse<StatusesResponse>>

    @PUT("issues/{issue_id}.json")
    fun updateIssueStatus(@Path("issue_id") issueId: Int, @Body() updateIssueStatusRequest: UpdateIssueStatusRequest)
            : LiveData<ApiResponse<Void>>

    @PUT("issues/{issue_id}.json?include=attachments")
    fun getAttachments(@Path("issue_id") issueId: Int): LiveData<ApiResponse<AttachmentsResponse>>
}