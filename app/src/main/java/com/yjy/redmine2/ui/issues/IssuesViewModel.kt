package com.yjy.redmine2.ui.issues

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.yjy.redmine2.db.AppDatabase
import com.yjy.redmine2.repository.IssueRepository

class IssuesViewModel(
    application: Application
) : AndroidViewModel(application) {
    private val issueRepository = IssueRepository(AppDatabase.getInstance(application))
    val issues = issueRepository.issues
}
