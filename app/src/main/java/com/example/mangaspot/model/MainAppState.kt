package com.example.mangaspot.model

import com.example.core.utils.theme.ApplicationThemes

data class MainAppState(
    val currentTheme: ApplicationThemes = ApplicationThemes.AUTOMATICALLY,
    val navigation: NavigationArgs = NavigationArgs.SplashNavigation
)