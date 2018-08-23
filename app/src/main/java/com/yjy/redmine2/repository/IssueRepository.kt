package com.yjy.redmine2.repository

import androidx.lifecycle.LiveData
import com.yjy.redmine2.common.ApiResponse
import com.yjy.redmine2.common.NetworkBoundResource
import com.yjy.redmine2.db.AppDatabase
import com.yjy.redmine2.db.model.IssueEntity
import com.yjy.redmine2.repository.model.Issue
import com.yjy.redmine2.server.Retrofit.server
import com.yjy.redmine2.server.model.IssuesRespone

class IssueRepository(
    val appDatabase: AppDatabase
) {

    val issues =
        object : NetworkBoundResource<List<Issue>, IssuesRespone>() {
            override fun saveCallResult(item: IssuesRespone) {
                appDatabase.issuesDao().insertIssues(
                    item.issues.map { issues ->
                        IssueEntity(
                            id = issues.id,
                            subject = issues.subject,
                            projectId = issues.priority.id,
                            authorId = issues.author.id,
                            assignToId = issues.assigned_to.id,
                            statusId = issues.status.id,
                            priorityId = issues.project.id
                        )
                    }
                )
            }

            override fun shouldFetch(data: List<Issue>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Issue>> {
                return appDatabase.issuesDao().getIssues()
            }

            override fun createCall(): LiveData<ApiResponse<IssuesRespone>> = server.getIssues()
        }.asLiveData()

    fun refresh() {

    }
}