package com.lvsecoto.redmine2.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "status")
data class StatusEntity(
    @PrimaryKey
    val statusId : Int,
    val name: String
)