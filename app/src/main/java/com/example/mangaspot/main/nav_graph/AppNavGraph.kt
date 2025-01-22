package com.example.mangaspot.main.nav_graph

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.core.utils.NavigationFactory
import com.example.library_api.LibraryFeatureApi

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavGraph(
    modifier: Modifier,
    navController: NavHostController,
    navigationFactories: Set<NavigationFactory>,
    libraryFeatureApi: LibraryFeatureApi,
    startDestination: String,
    onDestinationChange: (String) -> Unit = {}
) {
    SharedTransitionLayout(
        modifier = modifier,
    ) {
        NavHost(
            navController = navController,
            modifier = modifier,
            startDestination = startDestination,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None },

            ) {
            val builder = this

            navigationFactories.forEach {
                it.screen(this, navController)
            }

            with(libraryFeatureApi) { screen(builder, navController) }
        }
    }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        destination.route?.let { onDestinationChange(it) }
    }
}