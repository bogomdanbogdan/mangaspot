package com.example.core.utils.theme

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = BlueAccent,
    onPrimary = WhiteClear,
    secondary = BlueLight,
    onSurface = WhiteClear,
    onSurfaceVariant = WhiteClear,
    inversePrimary = Grey
)

private val LightColorScheme = lightColorScheme(
    primary = BlueAccent,
    onPrimary = DarkClear,
    secondary = BlueLight,
    surface = BlueLight,
    onSurface = DarkClear,
    background = WhiteClear,
    surfaceVariant = WhiteClear,
    onSurfaceVariant = DarkClear,
    surfaceTint = WhiteClear,
    inversePrimary = Grey
)

@Composable
fun MangaSpotTheme(
    context: Context = LocalContext.current,
    currentTheme: ApplicationThemes = ApplicationThemes.AUTOMATICALLY,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val isAutomaticallyDark = isSystemInDarkTheme()

    val currentScheme = when (currentTheme) {
        ApplicationThemes.DAY -> LightColorScheme
        ApplicationThemes.NIGHT -> DarkColorScheme
        ApplicationThemes.AUTOMATICALLY -> if (isAutomaticallyDark) DarkColorScheme else LightColorScheme
    }

    val colorScheme = if (dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        if (isAutomaticallyDark) {
            dynamicDarkColorScheme(context)
        } else {
            dynamicLightColorScheme(context)
        }
    } else {
        currentScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.surface.toArgb()
            window.navigationBarColor = colorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
                !isAutomaticallyDark
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = shape,
        typography = Typography,
        content = content
    )
}