package com.yjy.redmine2.server

import androidx.lifecycle.LiveData
import com.yjy.redmine2.common.ApiResponse
import com.yjy.redmine2.server.model.IssuesRespone
import com.yjy.redmine2.server.model.StatusesResponse
import retrofit2.http.GET

interface Server {

    @GET("issues.json")
    fun getIssues(): LiveData<ApiResponse<IssuesRespone>>

    @GET("issue_statuses.json")
    fun getStatuses() : LiveData<ApiResponse<StatusesResponse>>

}