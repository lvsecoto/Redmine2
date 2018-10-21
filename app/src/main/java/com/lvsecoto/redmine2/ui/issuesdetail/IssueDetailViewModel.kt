package com.yjy.redmine2.ui.issuesdetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.yjy.redmine2.db.AppDatabase
import com.yjy.redmine2.repository.IssueRepository

class IssueDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val issueRepository = IssueRepository(AppDatabase.getInstance(application))

    val issueId : MutableLiveData<Int> = MutableLiveData()

    val issueDetail  =
        Transformations.switchMap(issueId) {
            issueRepository.getIssueDetail(it)
        }!!

}
