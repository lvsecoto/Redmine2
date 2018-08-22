package com.yjy.redmine2.server

import retrofit2.http.GET

interface Server {

    @GET("issues/")
    fun getIssues()

}