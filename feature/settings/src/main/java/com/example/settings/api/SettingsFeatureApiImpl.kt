package com.example.settings.api

import android.content.Context
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.CompositionLocalProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.core.utils.anim.AnimationParams
import com.example.core.utils.anim.LocalAnimation
import com.example.core.utils.di.inject
import com.example.core.utils.navigation.NavigationFactory
import com.example.settings.di.DependencyProvider
import com.example.settings.ui.SettingsScreen
import com.example.settings.ui.SettingsViewModel
import com.example.settings_api.SettingsFeatureApi

class SettingsFeatureApiImpl(
    context: Context
) : SettingsFeatureApi, NavigationFactory {
    private val di = context.inject(DependencyProvider::class.java)

    override val route: String = "settings_graph"
    override val homeDestination = "settings"

    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun SharedTransitionScope.screen(
        builder: NavGraphBuilder,
        navController: NavHostController
    ) {
        builder.navigation(route = route, startDestination = homeDestination) {
            composable(homeDestination) {
                val settingsViewModel: SettingsViewModel = hiltViewModel()

                CompositionLocalProvider(
                    LocalAnimation provides AnimationParams("settings"),
                ) {
                    SettingsScreen(
                        settingsViewModel = settingsViewModel
                    )
                }
            }
        }
    }

    override fun screen(builder: NavGraphBuilder, navController: NavHostController) {

    }
}