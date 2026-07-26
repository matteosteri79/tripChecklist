package com.matteosteri.tripchecklist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.matteosteri.tripchecklist.data.database.AppDatabase
import com.matteosteri.tripchecklist.data.database.CategoryEntity
import com.matteosteri.tripchecklist.data.database.ChecklistEntity
import com.matteosteri.tripchecklist.data.database.ItemEntity
import com.matteosteri.tripchecklist.data.preset.PresetChecklist
import com.matteosteri.tripchecklist.repository.ChecklistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChecklistViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ChecklistRepository
    val checklists: StateFlow<List<ChecklistEntity>>

    init {
        val db = AppDatabase.Companion.getDatabase(application)
        repository = ChecklistRepository(db.checklistDao())

        checklists = repository.allChecklists
            .stateIn(viewModelScope, SharingStarted.Companion.Lazily, emptyList())
    }

    // CHECKLISTS
    fun addChecklist(name: String, icon: String? = null, onResult: (Long) -> Unit) {
        val formattedName = formatText(name)
        viewModelScope.launch {
            val id = repository.addChecklist(formattedName, icon)
            onResult(id)
        }
    }
    fun deleteChecklist(checklist: ChecklistEntity) {
        viewModelScope.launch {
            repository.deleteChecklist(checklist)
        }
    }
    fun getItemsByChecklist(checklistId: Long): Flow<List<ItemEntity>> {
        return repository.getItemsByChecklist(checklistId)
    }
    suspend fun getChecklistIcon(checklistId: Long): String? {
        return repository.getChecklistById(checklistId)?.icon
    }
    fun resetChecklist(checklistId: Long) {
        viewModelScope.launch {
            repository.resetChecklist(checklistId)
        }
    }

    fun renameChecklist(id: Long, newName: String) {
        viewModelScope.launch {
            repository.updateChecklistName(id, newName)
        }
    }

    fun importPreset(
        preset: PresetChecklist,
        onDone: (Long) -> Unit
    ) {
        viewModelScope.launch {

            // 1. crea checklist
            val checklistId = repository.addChecklist(preset.name, preset.icon)

            // 2. categorie + items
            preset.categories.forEach { cat ->

                val categoryId = repository.addCategory(
                    checklistId,
                    cat.name,
                    cat.icon
                )

                cat.items.forEach { item ->
                    repository.addItem(
                        ItemEntity(
                            checklistId = checklistId,
                            name = item.name,
                            categoryId = categoryId,
                            isChecked = false
                        )
                    )
                }
            }

            onDone(checklistId)
        }
    }



    // CATEGORIES
    fun addCategory(checklistId: Long, name: String) {
        val formattedName = formatText(name)
        viewModelScope.launch {
            repository.addCategory(checklistId, formattedName)
        }
    }
    fun getCategoriesByChecklist(checklistId: Long): Flow<List<CategoryEntity>> {
        return repository.getCategoriesByChecklist(checklistId)
    }
    fun deleteCategory(category: CategoryEntity) {
        viewModelScope.launch {
            repository.deleteItemsByCategory(category.id)
            repository.deleteCategory(category)
        }
    }
    fun deleteCategorySmart(category: CategoryEntity) {
        viewModelScope.launch {

            val categories = repository.getCategoriesByChecklistSync(category.checklistId)

            if (categories.size == 1) {
                // ultima categoria → elimina checklist
                val checklist = repository.getChecklistById(category.checklistId)

                if (checklist != null) {
                    repository.deleteChecklist(checklist)
                }
            } else {
                repository.deleteItemsByCategory(category.id)
                repository.deleteCategory(category)
            }
        }
    }



    // ITEMS
    fun toggleItem(item: ItemEntity) {
        viewModelScope.launch {
            repository.updateItem(
                item.copy(isChecked = !item.isChecked)
            )
        }
    }
    fun addItem(checklistId: Long, name: String, categoryId: Long) {
        val formattedName = formatText(name)
        viewModelScope.launch {
            repository.addItem(
                ItemEntity(
                    checklistId = checklistId,
                    categoryId = categoryId,
                    name = formattedName
                )
            )
        }
    }
    fun deleteItem(item: ItemEntity) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }
    fun deleteItemSmart(item: ItemEntity) {
        viewModelScope.launch {

            // 1️⃣ elimina SEMPRE l’item
            repository.deleteItem(item)

            // 2️⃣ controlla se restano item nella categoria
            val remainingItems = repository.getItemsByCategory(item.categoryId)

            if (remainingItems.isEmpty()) {
                val category = repository.getCategoryById(item.categoryId)

                if (category != null) {
                    deleteCategorySmart(category)
                }
            }
        }
    }

    fun getLastCategory(checklistId: Long): Flow<CategoryEntity?> {
        return repository.getLastCategory(checklistId)
    }

    private fun formatText(input: String): String {
        return input.trim()
            .lowercase()
            .replaceFirstChar { it.uppercase() }
    }

}