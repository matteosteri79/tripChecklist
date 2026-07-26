package com.matteosteri.tripchecklist.repository

import com.matteosteri.tripchecklist.data.database.CategoryEntity
import com.matteosteri.tripchecklist.data.database.ChecklistDao
import com.matteosteri.tripchecklist.data.database.ChecklistEntity
import com.matteosteri.tripchecklist.data.database.ItemEntity
import kotlinx.coroutines.flow.Flow

class ChecklistRepository(private val dao: ChecklistDao) {

    val allChecklists = dao.getAllChecklists()

    // CHECKLISTS
    suspend fun addChecklist(name: String, icon: String? = null): Long {
        return dao.insertChecklist(ChecklistEntity(name = name, icon = icon))
    }
    suspend fun deleteChecklist(checklist: ChecklistEntity) {
        dao.deleteChecklist(checklist)
    }
    fun getCategoriesByChecklist(checklistId: Long): Flow<List<CategoryEntity>> {
        return dao.getCategoriesByChecklist(checklistId)
    }
    suspend fun getChecklistById(id: Long): ChecklistEntity? {
        return dao.getChecklistById(id)
    }
    suspend fun getCategoriesByChecklistSync(checklistId: Long): List<CategoryEntity> {
        return dao.getCategoriesByChecklistSync(checklistId)
    }
    suspend fun resetChecklist(checklistId: Long) {
        dao.resetChecklist(checklistId)
    }
    suspend fun updateChecklistName(id: Long, newName: String) {
        dao.updateChecklistName(id, newName)
    }



    // CATEGORIES
    suspend fun addCategory(checklistId: Long, name: String,icon: String? = null): Long {
        return dao.insertCategory(
            CategoryEntity(
                checklistId = checklistId,
                name = name,
                icon = icon
            )
        )
    }
    fun getLastCategory(checklistId: Long): Flow<CategoryEntity?> {
        return dao.getLastCategory(checklistId)
    }
    suspend fun getCategoryById(id: Long): CategoryEntity? {
        return dao.getCategoryById(id)
    }
    suspend fun deleteCategory(category: CategoryEntity) {
        dao.deleteCategory(category)
    }
    suspend fun deleteItemsByCategory(categoryId: Long) {
        dao.deleteItemsByCategory(categoryId)
    }


    // ITEMS
    suspend fun addItem(item: ItemEntity) {
        dao.insertItem(item)
    }
    suspend fun updateItem(item: ItemEntity) {
        dao.updateItem(item)
    }
    suspend fun deleteItem(item: ItemEntity) {
        dao.deleteItem(item)
    }
    suspend fun getItemsByCategory(categoryId: Long): List<ItemEntity> {
        return dao.getItemsByCategory(categoryId)
    }

    fun getItemsByChecklist(checklistId: Long): Flow<List<ItemEntity>> {
        return dao.getItemsByChecklist(checklistId)
    }
}