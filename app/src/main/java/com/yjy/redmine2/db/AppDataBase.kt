package com.yjy.redmine2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.yjy.redmine2.db.model.AttachmentEntity
import com.yjy.redmine2.db.model.IssueEntity
import com.yjy.redmine2.db.model.StatusEntity

@Database(entities = [
    IssueEntity::class,
    StatusEntity::class,
    AttachmentEntity::class
], version = 1, exportSchema = false)
abstract class AppDatabase :RoomDatabase(), DaoComponent {

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "app.db")
                .fallbackToDestructiveMigrationFrom(1)
                .build()
    }
}
