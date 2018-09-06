package com.yjy.redmine2.ui.issues

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.yjy.redmine2.db.AppDatabase
import com.yjy.redmine2.repository.IssueRepository

class IssuesViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val issueRepository = IssueRepository(AppDatabase.getInstance(application))

    private val solvedIssuesId = MutableLiveData<Int>()

    val issues = issueRepository.issuesInList

    fun solveIssue(issuesId : Int) {
        solvedIssuesId.value = issuesId
    }
}
