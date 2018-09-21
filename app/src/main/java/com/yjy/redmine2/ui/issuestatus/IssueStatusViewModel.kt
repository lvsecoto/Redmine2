package com.yjy.redmine2.ui.issuestatus

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.yjy.redmine2.db.AppDatabase
import com.yjy.redmine2.repository.IssueRepository

class IssueStatusViewModel(application: Application) : AndroidViewModel(application) {
    private val issueRepository = IssueRepository(AppDatabase.getInstance(application))

    val issueId = MutableLiveData<Int>()

    val issueStatus = issueRepository.getStatusesInList()

    fun changeStatus(statusId: Int) {
        checkNotNull(issueId.value)
        issueId.value?.let {
            issueRepository.changeStatus(it, statusId)
        }
    }
}
