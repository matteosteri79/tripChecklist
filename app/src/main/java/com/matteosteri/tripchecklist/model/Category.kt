package com.matteosteri.tripchecklist.model

data class Category(
    val id: Int,
    var name: String,
    val items: MutableList<Item> = mutableListOf(),
    var isExpanded: Boolean = true
)