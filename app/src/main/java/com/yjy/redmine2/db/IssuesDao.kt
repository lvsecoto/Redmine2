package com.yjy.redmine2.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.yjy.redmine2.db.model.IssueEntity
import com.yjy.redmine2.repository.model.Issue

@Dao
abstract class IssuesDao {

    @Insert
    abstract fun insertIssues(issues : List<IssueEntity>)

    @Query("SELECT * FROM issues")
    abstract fun getIssues() : LiveData<List<Issue>>
}