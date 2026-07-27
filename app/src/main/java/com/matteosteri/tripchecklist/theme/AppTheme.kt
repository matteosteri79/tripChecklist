package com.matteosteri.tripchecklist.theme

enum class AppTheme(
    val id: String,
    val displayName: String
) {
    GREEN(
        id = "green",
        displayName = "Forest Green"
    ),
    RED(
        id = "red",
        displayName = "Racing Red"
    ),

    BLUE(
        id = "blue",
        displayName = "Ocean Blue"
    ),

    PURPLE(
        id = "purple",
        displayName = "Purple Energy"
    ),

    ORANGE(
        id = "orange",
        displayName = "Sunset Orange"
    );

    companion object {
        fun fromId(id: String?): AppTheme {
            return entries.firstOrNull { it.id == id } ?: BLUE
        }
    }
}