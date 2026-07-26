package com.matteosteri.tripchecklist.data.preset

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.matteosteri.tripchecklist.utils.LanguageManager
import com.matteosteri.tripchecklist.config.AppConfig

fun loadPresetChecklists(context: Context): List<PresetChecklist> {
    val templateFile = when (LanguageManager.getLanguage(context)) {
        LanguageManager.LANGUAGE_ENGLISH -> "templates_en.json"
        LanguageManager.LANGUAGE_FRENCH -> "templates_fr.json"
        LanguageManager.LANGUAGE_SPANISH -> "templates_es.json"
        LanguageManager.LANGUAGE_GERMAN -> "templates_de.json"
        else -> "templates_it.json"
    }
    val templatePath = "${AppConfig.TEMPLATE_SET}/$templateFile"

    val json = context.assets.open(templatePath)
        .bufferedReader()
        .use { it.readText() }

    val type = object : TypeToken<List<PresetChecklist>>() {}.type
    return Gson().fromJson(json, type)
}

fun getVisiblePresets(context: Context): List<PresetChecklist> {
    return loadPresetChecklists(context).filter { it.visible }
}