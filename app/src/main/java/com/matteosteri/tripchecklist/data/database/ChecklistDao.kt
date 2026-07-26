package com.matteosteri.tripchecklist.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ChecklistDao {

    // CHECKLISTS
    @Query("SELECT * FROM ChecklistEntity")
    fun getAllChecklists(): Flow<List<ChecklistEntity>>
    @Query("SELECT * FROM ItemEntity WHERE checklistId = :checklistId")
    fun getItemsByChecklist(checklistId: Long): Flow<List<ItemEntity>>
    @Insert
    suspend fun insertChecklist(checklist: ChecklistEntity): Long
    @Delete
    suspend fun deleteChecklist(checklist: ChecklistEntity)
    @Query("SELECT * FROM ChecklistEntity WHERE id = :id")
    suspend fun getChecklistById(id: Long): ChecklistEntity?
    @Query("UPDATE ItemEntity SET isChecked = 0 WHERE checklistId = :checklistId")
    suspend fun resetChecklist(checklistId: Long)

    @Query("UPDATE ChecklistEntity SET name = :newName WHERE id = :id")
    suspend fun updateChecklistName(id: Long, newName: String)

    // CATEGORIES
    @Insert
    suspend fun insertCategory(category: CategoryEntity): Long
    @Delete
    suspend fun deleteCategory(category: CategoryEntity)
    @Query("DELETE FROM ItemEntity WHERE categoryId = :categoryId")
    suspend fun deleteItemsByCategory(categoryId: Long)
    @Query("SELECT * FROM CategoryEntity WHERE checklistId = :checklistId")
    fun getCategoriesByChecklist(checklistId: Long): Flow<List<CategoryEntity>>
    @Query("SELECT * FROM CategoryEntity WHERE checklistId = :checklistId ORDER BY id DESC LIMIT 1")
    fun getLastCategory(checklistId: Long): Flow<CategoryEntity?>
    @Query("SELECT * FROM ItemEntity WHERE categoryId = :categoryId")
    suspend fun getItemsByCategory(categoryId: Long): List<ItemEntity>
    @Query("SELECT * FROM CategoryEntity WHERE id = :id")
    suspend fun getCategoryById(id: Long): CategoryEntity?
    @Query("SELECT * FROM CategoryEntity WHERE checklistId = :checklistId")
    suspend fun getCategoriesByChecklistSync(checklistId: Long): List<CategoryEntity>


    // ITEMS
    @Insert
    suspend fun insertItem(item: ItemEntity)
    @Update
    suspend fun updateItem(item: ItemEntity)
    @Delete
    suspend fun deleteItem(item: ItemEntity)
}