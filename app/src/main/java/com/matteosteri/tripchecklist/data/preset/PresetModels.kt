package com.matteosteri.tripchecklist.data.preset

data class PresetChecklist(
    val id: String,
    val name: String,
    val icon: String?,
    val visible: Boolean,
    val categories: List<PresetCategory>
)

data class PresetCategory(
    val name: String,
    val icon: String?,
    val items: List<PresetItem>
)

data class PresetItem(
    val name: String
)