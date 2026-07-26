package com.matteosteri.tripchecklist.data.preset

import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Backpack
import androidx.compose.material.icons.filled.Checkroom
import androidx.compose.material.icons.filled.CleanHands
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BeachAccess
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.Hiking
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Light
import androidx.compose.material.icons.filled.Luggage
import androidx.compose.material.icons.filled.Pool
import androidx.compose.material.icons.filled.Sports
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.vector.ImageVector

object PresetIconMapper {
    fun getIcon(name: String?): ImageVector {
        return when (name?.lowercase()) {
            "timeline" -> Icons.Default.Timeline
            "food" -> Icons.Default.Restaurant
            "race" -> Icons.Default.EmojiEvents
            "flight" -> Icons.Default.Flight
            "description" -> Icons.Default.Description
            "checkroom" -> Icons.Default.Checkroom
            "devices" -> Icons.Default.Devices
            "medical_services" -> Icons.Default.MedicalServices
            "backpack" -> Icons.Default.Backpack
            "clean_hands" -> Icons.Default.CleanHands
            "work" -> Icons.Default.Work
            "beach_access" -> Icons.Default.BeachAccess
            "wb_sunny" -> Icons.Default.WbSunny
            "pool" -> Icons.Default.Pool
            "sports" -> Icons.Default.Sports
            "terrain" -> Icons.Default.Terrain
            "hiking" -> Icons.Default.Hiking
            "health_and_safety" -> Icons.Default.HealthAndSafety
            "camping" -> Icons.Default.Home
            "restaurant" -> Icons.Default.Restaurant
            "light" -> Icons.Default.Light
            "build" -> Icons.Default.Build
            "directions_car" -> Icons.Default.DirectionsCar
            "navigation" -> Icons.Default.Map
            "luggage" -> Icons.Default.Luggage
            "warning" -> Icons.Default.Warning

            else -> Icons.AutoMirrored.Filled.List
        }
    }
}