package com.matteosteri.tripchecklist.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val checklistId: Long,
    val categoryId: Long,
    val name: String,
    val isChecked: Boolean = false
)