package com.yjy.redmine2.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yjy.redmine2.db.model.IssueEntity
import com.yjy.redmine2.db.model.StatusEntity
import com.yjy.redmine2.repository.model.IssueInList

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

    @Query("SELECT id, subject, statusName, priorityName, projectName, assignTo, authorName FROM issue")
    abstract fun getIssuesInList() : LiveData<List<IssueInList>>

    @Query("SELECT * FROM status")
    abstract fun getStatusEntities(): LiveData<List<StatusEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertStatuesEntities(statusEntities: List<StatusEntity>)
}