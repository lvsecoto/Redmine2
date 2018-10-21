package com.yjy.redmine2.ui.issuestatus

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.yjy.redmine2.common.Resource
import com.yjy.redmine2.db.AppDatabase
import com.yjy.redmine2.repository.IssueRepository

class IssueStatusViewModel(application: Application) : AndroidViewModel(application) {
    private val issueRepository = IssueRepository(AppDatabase.getInstance(application))

    val issueId = MutableLiveData<Int>()

    val issueStatus = issueRepository.getStatusesInList()

    private val changedIssueStatusId = MutableLiveData<Int>()

    val changedIssueStatus =
        Transformations.switchMap(changedIssueStatusId) { statusId ->
            checkNotNull(issueId.value)

            issueId.value?.let { issueId ->

                Transformations.map(issueRepository.changeIssueStatus(issueId, statusId)) {
                    Resource(it.status, it.data?.statusId == statusId, it.message)
                }

            }
        }!!

    fun changeStatus(statusId: Int) {
        changedIssueStatusId.value = statusId
    }

}
