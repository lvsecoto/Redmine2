package com.yjy.redmine2.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.yjy.redmine2.common.AbsentLiveData
import com.yjy.redmine2.common.ApiResponse
import com.yjy.redmine2.common.NetworkBoundResource
import com.yjy.redmine2.db.AppDatabase
import com.yjy.redmine2.db.model.IssueEntity
import com.yjy.redmine2.db.model.StatusEntity
import com.yjy.redmine2.repository.model.IssueInList
import com.yjy.redmine2.server.Retrofit.server
import com.yjy.redmine2.server.model.IssuesRespone
import com.yjy.redmine2.server.model.StatusesResponse
import com.yjy.redmine2.server.model.UpdateIssueStatusRequest

class IssueRepository(
    val appDatabase: AppDatabase
) {

    fun getIssuesInList() {
        val a = object : MediatorLiveData<Void>() {
            val a : ArrayList<LiveData<*>> = arrayListOf(
                issuesEntity,
                statusesEntity
            )
            init {
                addSource(a[0], object : Observer<Any>{
                    override fun onChanged(t: Any?) {
                        removeSource(a[0])
                        addSource(a[1], this)
                    }
                })
            }
        }
    }

    val issuesInList =
        object : NetworkBoundResource<List<IssueInList>, IssuesRespone>() {
            override fun saveCallResult(item: IssuesRespone) {
            }

            override fun shouldFetch(data: List<IssueInList>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<IssueInList>> {
                return appDatabase.issuesDao.getIssuesInList()
            }

            override fun createCall(): LiveData<ApiResponse<IssuesRespone>> = server.getIssues()
        }.asLiveData()

    val issuesEntity =
            object : NetworkBoundResource<List<IssueEntity>, IssuesRespone>() {
                override fun saveCallResult(item: IssuesRespone) {
                    appDatabase.issuesDao.deleteAndInsertIssueEntities(
                        item.issues.map {
                            IssueEntity(
                                id = it.id,
                                subject = it.subject,
                                projectName = it.project.name,
                                assignTo = it.assigned_to.name,
                                statusName = it.status.name,
                                authorName = it.author.name,
                                priorityName = it.priority.name,
                                statusId = it.status.id
                            )
                        }
                    )
                }

                override fun shouldFetch(data: List<IssueEntity>?): Boolean = true

                override fun loadFromDb(): LiveData<List<IssueEntity>> =
                    appDatabase.issuesDao.getIssueEntities()

                override fun createCall(): LiveData<ApiResponse<IssuesRespone>> = server.getIssues()

            }.asLiveData()

    val statusesEntity =
        object : NetworkBoundResource<List<StatusEntity>, StatusesResponse>() {

            override fun saveCallResult(item: StatusesResponse) {
                appDatabase.issuesDao.insertStatuesEntities(
                    item.issue_statuses.map {
                        StatusEntity(
                            id = it.id,
                            name = it.name
                        )
                    })
            }

            override fun shouldFetch(data: List<StatusEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<List<StatusEntity>> =
                appDatabase.issuesDao.getStatusEntities()


            override fun createCall(): LiveData<ApiResponse<StatusesResponse>> =
                server.getStatuses()
        }.asLiveData()

    fun solvedIssue(issueId : Int) =
            object : NetworkBoundResource<Void, Void>() {
                override fun saveCallResult(item: Void) {
                    appDatabase.issuesDao.updateIssueStatus(issueId, 3)
                }

                override fun shouldFetch(data: Void?): Boolean = true

                override fun loadFromDb(): LiveData<Void> = AbsentLiveData.create()

                override fun createCall(): LiveData<ApiResponse<Void>> {
                    return server.updateIssueStatus(issueId, UpdateIssueStatusRequest())
                }
            }

    fun refresh() {

    }
}