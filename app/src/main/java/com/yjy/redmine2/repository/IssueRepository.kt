package com.yjy.redmine2.repository

import androidx.lifecycle.LiveData
import com.yjy.redmine2.common.ApiResponse
import com.yjy.redmine2.common.NetworkBoundResource
import com.yjy.redmine2.db.AppDatabase
import com.yjy.redmine2.db.model.IssueEntity
import com.yjy.redmine2.db.model.StatusEntity
import com.yjy.redmine2.repository.model.IssueInList
import com.yjy.redmine2.server.Retrofit.server
import com.yjy.redmine2.server.model.IssuesRespone
import com.yjy.redmine2.server.model.StatusesResponse

class IssueRepository(
    val appDatabase: AppDatabase
) {

    val issues =
        object : NetworkBoundResource<List<IssueInList>, IssuesRespone>() {
            override fun saveCallResult(item: IssuesRespone) {
                appDatabase.issuesDao.deleteAndInsertIssues(
                    item.issues.map { issues ->
                        IssueEntity(
                            id = issues.id,
                            subject = issues.subject,
                            projectName = issues.priority.name,
                            authorName = issues.author.name,
                            assignTo = issues.assigned_to.name,
                            statusName = issues.status.name,
                            priorityName = issues.project.name
                        )
                    }
                )
            }

            override fun shouldFetch(data: List<IssueInList>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<IssueInList>> {
                return appDatabase.issuesDao.getIssuesInList()
            }

            override fun createCall(): LiveData<ApiResponse<IssuesRespone>> = server.getIssues()
        }.asLiveData()

    val statueEntity =
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

    fun refresh() {

    }
}