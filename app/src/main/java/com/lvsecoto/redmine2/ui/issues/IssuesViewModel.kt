package com.lvsecoto.redmine2.ui.issues

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.lvsecoto.redmine2.db.AppDatabase
import com.lvsecoto.redmine2.repository.IssueRepository
import com.lvsecoto.redmine2.repository.model.IssueInList

class IssuesViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val issueRepository = IssueRepository(AppDatabase.getInstance(application))

    val issues = issueRepository.getIssuesInList()

    private val solveIssueId = MutableLiveData<IssueInList>()
    val solvedIssue = Transformations.switchMap(solveIssueId) {
        when (it.statusId) {
            3 -> issueRepository.changeIssueStatus(it.issueId, 2)
            else -> issueRepository.changeIssueStatus(it.issueId, 3)
        }
    }!!

    fun solveIssue(issueInList: IssueInList) {
        solveIssueId.value = issueInList
    }
}
