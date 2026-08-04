package com.matteosteri.tripchecklist.theme

import android.content.Context
import com.matteosteri.tripchecklist.config.AppConfig

object ThemeManager {
    private const val KEY_THEME = "theme"

    fun getTheme(context: Context): AppTheme {
        val prefs = context.applicationContext.getSharedPreferences(
            AppConfig.PREFS_NAME,
            Context.MODE_PRIVATE
        )

        val themeId = prefs.getString(
            KEY_THEME,
            AppConfig.DEFAULT_THEME
        )

        return AppTheme.fromId(themeId)
    }

    fun setTheme(
        context: Context,
        theme: AppTheme
    ) {
        val prefs = context.applicationContext.getSharedPreferences(
            AppConfig.PREFS_NAME,
            Context.MODE_PRIVATE
        )

        prefs.edit()
            .putString(KEY_THEME, theme.id)
            .commit()
    }
}