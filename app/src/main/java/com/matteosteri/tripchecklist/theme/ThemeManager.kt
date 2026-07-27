package com.matteosteri.tripchecklist.theme

import android.content.Context

object ThemeManager {

    private const val PREFS_NAME = "trip_checklist_preferences"
    private const val KEY_THEME = "theme"

    fun getTheme(context: Context): AppTheme {
        val prefs = context.applicationContext.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )

        val themeId = prefs.getString(
            KEY_THEME,
            AppTheme.BLUE.id
        )

        return AppTheme.fromId(themeId)
    }

    fun setTheme(
        context: Context,
        theme: AppTheme
    ) {
        val prefs = context.applicationContext.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )

        prefs.edit()
            .putString(KEY_THEME, theme.id)
            .commit()
    }
}