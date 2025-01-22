package com.example.settings_api

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface SettingsFeatureApi {
    val route: String

    @OptIn(ExperimentalSharedTransitionApi::class)
    fun SharedTransitionScope.screen(builder: NavGraphBuilder, navController: NavHostController)
}