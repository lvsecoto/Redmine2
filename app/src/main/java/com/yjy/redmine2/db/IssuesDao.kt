package com.yjy.redmine2.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yjy.redmine2.db.model.IssueEntity
import com.yjy.redmine2.db.model.StatusEntity
import com.yjy.redmine2.repository.model.IssueDetail
import com.yjy.redmine2.repository.model.IssueInList

@Dao
abstract class IssuesDao {

    @Query("DELETE FROM issue")
    abstract fun deleteIssueEntities()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertIssueEntities(issues: List<IssueEntity>)

    @Transaction
    open fun deleteAndInsertIssueEntities(issues: List<IssueEntity>) {
        deleteIssueEntities()
        insertIssueEntities(issues)
    }

    @Query("SELECT * FROM issue")
    abstract fun getIssueEntities(): LiveData<List<IssueEntity>>

    @Query("SELECT * FROM issue WHERE issueId == :issueId")
    abstract fun getIssueEntity(issueId: Int): LiveData<IssueEntity>

    @Query("DELETE FROM status")
    abstract fun deleteStatuesEntities()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertStatuesEntities(statusEntities: List<StatusEntity>)

    @Transaction
    open fun deleteAndInsertStatuesEntities(statusEntities: List<StatusEntity>) {
        deleteStatuesEntities()
        insertStatuesEntities(statusEntities)
    }

    @Query("SELECT * FROM status")
    abstract fun getStatusEntities(): LiveData<List<StatusEntity>>

    @Query("UPDATE issue SET statusId = :statusId WHERE issueId == :issueId")
    abstract fun updateIssueStatus(issueId: Int, statusId: Int)

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query(
        """
        SELECT issueId, subject, statusId, status.name as statusName ,priorityName, projectName, assignTo, authorName, statusId FROM issue
        JOIN status USING(statusId)
        """
    )
    abstract fun getIssuesInList(): LiveData<List<IssueInList>>

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query(
        """
        SELECT issueId, subject
        FROM issue
        WHERE issueId == :issueId
        """
    )
    abstract fun getIssueDetail(issueId: Int): LiveData<IssueDetail>
}