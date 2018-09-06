package com.yjy.redmine2.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yjy.redmine2.db.model.IssueEntity
import com.yjy.redmine2.db.model.StatusEntity
import com.yjy.redmine2.repository.model.IssueInList

@Dao
abstract class IssuesDao {

    @Query("DELETE FROM issue")
    abstract fun deleteIssueEntities()

    @Insert()
    abstract fun insertIssueEntities(issues : List<IssueEntity>)

    @Transaction
    open fun deleteAndInsertIssueEntities(issues : List<IssueEntity>) {
        deleteIssueEntities()
        insertIssueEntities(issues)
    }

    @Query("SELECT * FROM issue")
    abstract fun getIssueEntities() : LiveData<List<IssueEntity>>

    @Query("DELETE FROM status")
    abstract fun deleteStatuesEntities()

    @Insert()
    abstract fun insertStatuesEntities(statusEntities: List<StatusEntity>)

    @Transaction
    open fun deleteAndInsertStatuesEntities(statusEntities: List<StatusEntity>) {
        deleteStatuesEntities()
        insertStatuesEntities(statusEntities)
    }

    @Query("SELECT * FROM status")
    abstract fun getStatusEntities(): LiveData<List<StatusEntity>>

    @Query("SELECT id, subject, statusName, priorityName, projectName, assignTo, authorName FROM issue")
    abstract fun getIssuesInList() : LiveData<List<IssueInList>>

    @Query("UPDATE issue SET statusName = :statusId WHERE id == :issueId")
    abstract fun updateIssueStatus(issueId: Int, statusId: Int)

}