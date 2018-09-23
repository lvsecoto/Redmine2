package com.yjy.redmine2.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.yjy.redmine2.db.model.AttachmentEntity
import com.yjy.redmine2.db.model.IssueEntity
import com.yjy.redmine2.db.model.StatusEntity
import com.yjy.redmine2.repository.model.IssueDetail
import com.yjy.redmine2.repository.model.IssueInList
import com.yjy.redmine2.repository.model.StatusInList

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
    @Transaction
    @Query(
        """
        SELECT issueId, subject, name as status
        FROM issue
        JOIN status USING (statusId)
        WHERE issueId == :issueId
        """
    )
    abstract fun getIssueDetail(issueId: Int): LiveData<IssueDetail>

    @Query(
        """
        SELECT *
        FROM attachment
        """
    )
    abstract fun getAttachments(): LiveData<List<AttachmentEntity>>

    @Insert
    abstract fun insertAttachment(attachmentEntities: List<AttachmentEntity>)

    @Query(
        """
        DELETE
        FROM attachment
        WHERE issueId == :issueId
        """
    )
    abstract fun deleteAttachmentByIssueId(issueId: Int)

    @Transaction
    open fun insertAndDeleteAttachments(issueId: Int, attachmentEntities: List<AttachmentEntity>) {
        deleteAttachmentByIssueId(issueId)
        insertAttachment(attachmentEntities)
    }

    @Query(
        """
        SELECT statusId, name FROM status
    """
    )
    abstract fun getStatusesInList(): LiveData<List<StatusInList>>

}