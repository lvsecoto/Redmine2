package com.yjy.redmine2.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [
    AppDataBase::class
], version = 1)
abstract class AppDataBase : RoomDatabase() {
}
