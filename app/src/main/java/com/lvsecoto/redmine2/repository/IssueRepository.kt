package com.lvsecoto.redmine2.repository

import androidx.lifecycle.LiveData
import com.lvsecoto.redmine2.common.AbsentLiveData
import com.lvsecoto.redmine2.common.ApiResponse
import com.lvsecoto.redmine2.common.NetworkBoundResource
import com.lvsecoto.redmine2.common.livedata.queueApi
import com.lvsecoto.redmine2.db.AppDatabase
import com.lvsecoto.redmine2.db.model.AttachmentEntity
import com.lvsecoto.redmine2.db.model.IssueEntity
import com.lvsecoto.redmine2.db.model.StatusEntity
import com.lvsecoto.redmine2.server.Retrofit.server
import com.lvsecoto.redmine2.server.model.*

class IssueRepository(
    val appDatabase: AppDatabase
) {

    fun getIssuesInList() = queueApi(
        appDatabase.issuesDao.getIssuesInList(),
        getStatusEntities(),
        getIssueEntities()
    )

    fun getIssueDetail(issueId: Int) = queueApi(
        appDatabase.issuesDao.getIssueDetail(issueId),
        getIssueEntity(issueId),
        getAttachments(issueId)
    )

    fun getStatusesInList() = queueApi(
        appDatabase.issuesDao.getStatusesInList(),
        getStatusEntities()
    )

    private fun getIssueEntities() =
        object : NetworkBoundResource<List<IssueEntity>, IssuesResponse>() {
            override fun saveCallResult(item: IssuesResponse) {
                appDatabase.issuesDao.deleteAndInsertIssueEntities(
                    item.issues.map {
                        IssueEntity(
                            issueId = it.id,
                            subject = it.subject,
                            projectName = it.project.name,
                            assignTo = it.assigned_to.name,
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

            override fun createCall(): LiveData<ApiResponse<IssuesResponse>> = server.getIssues()

        }.asLiveData()

    private fun getIssueEntity(issueId: Int, isDirty: Boolean = false) =
        object : NetworkBoundResource<IssueEntity, IssueResponse>() {
            override fun saveCallResult(item: IssueResponse) {
                appDatabase.issuesDao.insertIssueEntities(listOf(
                    item.issue.let {
                        IssueEntity(
                            issueId = it.id,
                            subject = it.subject,
                            projectName = it.project.name,
                            assignTo = it.assigned_to.name,
                            authorName = it.author.name,
                            priorityName = it.priority.name,
                            statusId = it.status.id
                        )
                    }
                ))
            }

            override fun shouldFetch(data: IssueEntity?): Boolean = isDirty

            override fun loadFromDb(): LiveData<IssueEntity> =
                appDatabase.issuesDao.getIssueEntity(issueId)

            override fun createCall(): LiveData<ApiResponse<IssueResponse>> =
                server.getIssue(issueId)
        }.asLiveData()

    private fun getAttachments(issueId: Int) =
        object : NetworkBoundResource<List<AttachmentEntity>, AttachmentsResponse>() {
            override fun saveCallResult(item: AttachmentsResponse) {
                appDatabase.issuesDao.insertAndDeleteAttachments(issueId,
                    item.issue.attachments
                        .map {
                            AttachmentEntity(
                                attachmentId = it.id,
                                issueId = issueId,
                                contentUrl = it.content_url,
                                fileName = it.filename,
                                authorName = it.author.name
                            )
                        })
            }

            override fun shouldFetch(data: List<AttachmentEntity>?): Boolean = true

            override fun loadFromDb(): LiveData<List<AttachmentEntity>> =
                appDatabase.issuesDao.getAttachments()

            override fun createCall(): LiveData<ApiResponse<AttachmentsResponse>> =
                server.getAttachments(issueId)
        }.asLiveData()

    private fun getStatusEntities() =
        object : NetworkBoundResource<List<StatusEntity>, StatusesResponse>() {

            override fun saveCallResult(item: StatusesResponse) {
                appDatabase.issuesDao.insertStatuesEntities(
                    item.issue_statuses.map {
                        StatusEntity(
                            statusId = it.id,
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

    private fun updateIssueStatus(issueId: Int, statusId: Int) =
        object : NetworkBoundResource<Void, Void>() {
            override fun saveCallResult(item: Void) {
            }

            override fun shouldFetch(data: Void?): Boolean = true

            override fun loadFromDb(): LiveData<Void> = AbsentLiveData.create()

            override fun createCall(): LiveData<ApiResponse<Void>> {
                return server.updateIssueStatus(issueId, UpdateIssueStatusRequest(statusId))
            }
        }.asLiveData()

    fun changeIssueStatus(issueId: Int, statusId: Int) =
        queueApi(
            appDatabase.issuesDao.getIssueEntity(issueId),
            updateIssueStatus(issueId, statusId),
            getIssueEntity(issueId, true)
        )
}