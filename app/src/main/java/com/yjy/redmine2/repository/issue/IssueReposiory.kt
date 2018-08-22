package com.yjy.redmine2.repository.issue

import androidx.lifecycle.LiveData
import com.yjy.redmine2.repository.NetworkBoundResource
import com.yjy.redmine2.server.ApiResponse

class IssueReposiory {
    fun abc()= {
        object : NetworkBoundResource<String, String>(){
            override fun saveCallResult(item: String) {

            }

            override fun shouldFetch(data: String?): Boolean {

            }

            override fun loadFromDb(): LiveData<String> {
            }

            override fun createCall(): LiveData<ApiResponse<String>> {
            }
        }.asLiveData()
    }
}