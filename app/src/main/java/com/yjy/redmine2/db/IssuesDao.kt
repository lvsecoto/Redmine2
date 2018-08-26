package com.yjy.redmine2.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.yjy.redmine2.db.model.IssueEntity
import com.yjy.redmine2.repository.model.IssueList

@Dao
abstract class IssuesDao {

    @Query("DELETE FROM issue")
    abstract fun deleteAllIssues()

    @Insert()
    abstract fun insertIssues(issues : List<IssueEntity>)

    @Transaction
    open fun deleteAndInsertIssues(issues : List<IssueEntity>) {
        deleteAllIssues()
        insertIssues(issues)
    }

    @Query("SELECT id, subject FROM issue")
    abstract fun getIssues() : LiveData<List<IssueList>>
}