package com.matteosteri.tripchecklist.theme

import android.app.Activity
import android.graphics.Color
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = GreenDark,
    secondary = GreenLight,
    tertiary = Green,

    background = White,
    surface = White,

    onPrimary = White,
    onSecondary = Black,
    onBackground = Black,
    onSurface = Black
)

private val DarkColorScheme = darkColorScheme(
    primary = GreenLight,
    secondary = Green,
    tertiary = GreenDark,

    background = Black,
    surface = GreenDark,

    onPrimary = Black,
    onSecondary = White,
    onBackground = White,
    onSurface = White
)
private val BlueLightColorScheme = lightColorScheme(
    primary = BlueDark,
    secondary = BlueLight,
    tertiary = Blue,

    background = White,
    surface = White,

    onPrimary = White,
    onSecondary = Black,
    onBackground = Black,
    onSurface = Black
)
private val OrangeLightColorScheme = lightColorScheme(
    primary = OrangeDark,
    secondary = OrangeLight,
    tertiary = Orange,

    background = White,
    surface = White,

    onPrimary = White,
    onSecondary = Black,
    onBackground = Black,
    onSurface = Black
)
private val RedLightColorScheme = lightColorScheme(
    primary = RedDark,
    secondary = RedLight,
    tertiary = Red,

    background = White,
    surface = White,

    onPrimary = White,
    onSecondary = Black,
    onBackground = Black,
    onSurface = Black
)
private val PurpleLightColorScheme = lightColorScheme(
    primary = PurpleDark,
    secondary = PurpleLight,
    tertiary = Purple,

    background = White,
    surface = White,

    onPrimary = White,
    onSecondary = Black,
    onBackground = Black,
    onSurface = Black
)
private val BlueDarkColorScheme = darkColorScheme(
    primary = BlueLight,
    secondary = Blue,
    tertiary = BlueDark,

    background = Black,
    surface = BlueDark,

    onPrimary = Black,
    onSecondary = White,
    onBackground = White,
    onSurface = White
)
private val OrangeDarkColorScheme = darkColorScheme(
    primary = OrangeLight,
    secondary = Orange,
    tertiary = OrangeDark,

    background = Black,
    surface = OrangeDark,

    onPrimary = Black,
    onSecondary = White,
    onBackground = White,
    onSurface = White
)
private val RedDarkColorScheme = darkColorScheme(
    primary = RedLight,
    secondary = Red,
    tertiary = RedDark,

    background = Black,
    surface = RedDark,

    onPrimary = Black,
    onSecondary = White,
    onBackground = White,
    onSurface = White
)
private val PurpleDarkColorScheme = darkColorScheme(
    primary = PurpleLight,
    secondary = Purple,
    tertiary = PurpleDark,

    background = Black,
    surface = PurpleDark,

    onPrimary = Black,
    onSecondary = White,
    onBackground = White,
    onSurface = White
)

@Composable
fun SportChecklistTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val selectedTheme = ThemeManager.getTheme(context)
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        else -> {
            when (selectedTheme) {
                AppTheme.GREEN ->
                    if (darkTheme) DarkColorScheme
                    else LightColorScheme
                AppTheme.BLUE ->
                    if (darkTheme) BlueDarkColorScheme
                    else BlueLightColorScheme
                AppTheme.ORANGE ->
                    if (darkTheme) OrangeDarkColorScheme
                    else OrangeLightColorScheme
                AppTheme.RED ->
                    if (darkTheme) RedDarkColorScheme
                    else RedLightColorScheme
                AppTheme.PURPLE ->
                    if (darkTheme) PurpleDarkColorScheme
                    else PurpleLightColorScheme
            }
        }
    }

    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window

            window.statusBarColor = Color.TRANSPARENT

            WindowCompat.getInsetsController(window, view)
                .isAppearanceLightStatusBars = true
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}