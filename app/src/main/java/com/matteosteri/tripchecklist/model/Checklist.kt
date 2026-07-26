package com.matteosteri.tripchecklist.model

data class Checklist(
    val id: Int,
    var name: String,
    val categories: MutableList<Category> = mutableListOf()
)