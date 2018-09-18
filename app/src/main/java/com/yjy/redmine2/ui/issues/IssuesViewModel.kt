package com.yjy.redmine2.ui.issues

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.yjy.redmine2.common.Resource
import com.yjy.redmine2.db.AppDatabase
import com.yjy.redmine2.repository.IssueRepository
import com.yjy.redmine2.repository.model.IssueInList

class IssuesViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val issueRepository = IssueRepository(AppDatabase.getInstance(application))

    val issues = issueRepository.getIssuesInList()

    private val solveIssueId  = MutableLiveData<IssueInList>()
    val solvedIssue: LiveData<Resource<Any>> = Transformations.switchMap(solveIssueId) {
        when(it.statusId) {
            3 -> issueRepository.solveIssue(it.issueId, 2)
            else -> issueRepository.solveIssue(it.issueId, 3)
        }
    }

    fun solveIssue(issueInList: IssueInList) {
        solveIssueId.value = issueInList
    }
}
