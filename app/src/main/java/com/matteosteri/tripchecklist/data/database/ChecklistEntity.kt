package com.matteosteri.tripchecklist.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChecklistEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val icon: String? = null
)