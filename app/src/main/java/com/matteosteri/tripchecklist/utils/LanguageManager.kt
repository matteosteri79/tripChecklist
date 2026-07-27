package com.matteosteri.tripchecklist.utils

import android.content.Context
import android.content.res.Configuration
import java.util.Locale

object LanguageManager {
    const val LANGUAGE_SYSTEM = ""
    const val LANGUAGE_ITALIAN = "it"
    const val LANGUAGE_ENGLISH = "en"
    const val LANGUAGE_FRENCH = "fr"
    const val LANGUAGE_SPANISH = "es"
    const val LANGUAGE_GERMAN = "de"
    private const val PREFS_NAME = "trip_checklist_preferences"
    private const val KEY_LANGUAGE = "language"

    fun getLanguage(context: Context): String {
        val appContext = context.applicationContext
        return appContext
            .getSharedPreferences(
                PREFS_NAME,
                Context.MODE_PRIVATE
            )
            .getString(KEY_LANGUAGE, LANGUAGE_SYSTEM)
            ?: LANGUAGE_SYSTEM
    }

    fun setLanguage(context: Context, language: String) {
        val appContext = context.applicationContext

        appContext
            .getSharedPreferences(
                PREFS_NAME,
                Context.MODE_PRIVATE
            )
            .edit()
            .putString(KEY_LANGUAGE, language)
            .commit()
    }

    fun applyLanguage(context: Context): Context {
        val language = getLanguage(context)
        if (language.isBlank()) {
            return context
        }

        val locale = Locale(language)
        val configuration = Configuration(
            context.resources.configuration
        )
        configuration.setLocale(locale)

        return context.createConfigurationContext(configuration)
    }
}