package com.yjy.redmine2.server

import androidx.lifecycle.LiveData
import com.yjy.redmine2.common.ApiResponse
import com.yjy.redmine2.server.model.IssuesRespone
import com.yjy.redmine2.server.model.StatusesResponse
import com.yjy.redmine2.server.model.UpdateIssueStatusRequest
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface Server {

    @GET("issues.json")
    fun getIssues(): LiveData<ApiResponse<IssuesRespone>>

    @GET("issue_statuses.json")
    fun getStatuses(): LiveData<ApiResponse<StatusesResponse>>

    @PUT("issues/{issue_id}.json")
    fun updateIssueStatus(@Path("issue_id") issueId: Int, updateIssueStatusRequest: UpdateIssueStatusRequest)
            : LiveData<ApiResponse<Void>>

}