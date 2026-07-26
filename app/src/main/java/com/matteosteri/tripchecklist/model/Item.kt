package com.matteosteri.tripchecklist.model

data class Item(
    val id: Int,
    var name: String,
    var isChecked: Boolean = false
)