package com.yjy.redmine2.ui.issues

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.yjy.redmine2.common.ApiResponse
import com.yjy.redmine2.common.NetworkBoundResource
import com.yjy.redmine2.server.Retrofit
import com.yjy.redmine2.server.model.IssuesRespone

class IssuesViewModel : ViewModel() {
    private val server = Retrofit.server

    val issues =
        object : NetworkBoundResource<List<String>, IssuesRespone>() {
            override fun saveCallResult(item: IssuesRespone) {

            }

            override fun shouldFetch(data: List<String>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<String>> {
                return object : LiveData<List<String>>() {
                    override fun onActive() {
                        value = listOf("123", "456")
                    }
                }
            }

            override fun createCall(): LiveData<ApiResponse<IssuesRespone>> = server.getIssues()
        }.asLiveData()
}
