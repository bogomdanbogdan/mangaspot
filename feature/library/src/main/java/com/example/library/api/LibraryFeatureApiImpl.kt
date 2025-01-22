package com.example.library.api

import android.content.Context
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.CompositionLocalProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.core.utils.AnimationParams
import com.example.core.utils.LocalAnimation
import com.example.core.utils.NavigationFactory
import com.example.core.utils.inject
import com.example.library.di.DependencyProvider
import com.example.library.ui.CamListViewModel
import com.example.library.ui.LibraryScreen
import com.example.library_api.LibraryFeatureApi

class LibraryFeatureApiImpl(
    context: Context
) : LibraryFeatureApi, NavigationFactory {
    private val di = context.inject(DependencyProvider::class.java)

    override val route: String = "home_graph"
    private val homeDestination = "home"

    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun SharedTransitionScope.screen(
        builder: NavGraphBuilder,
        navController: NavHostController
    ) {
        builder.navigation(route = route, startDestination = homeDestination) {
            composable(homeDestination) {
                val camListViewModel: CamListViewModel = hiltViewModel()

                CompositionLocalProvider(
                    LocalAnimation provides AnimationParams("collection"),
                ) {
                    LibraryScreen(
                        camListViewModel = camListViewModel
                    )
                }
            }
        }
    }

    override fun screen(builder: NavGraphBuilder, navController: NavHostController) {
        
    }
}