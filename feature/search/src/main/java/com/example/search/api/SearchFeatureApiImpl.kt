package com.example.search.api

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
import com.example.search.di.DependencyProvider
import com.example.search.ui.SearchScreen
import com.example.search.ui.SearchViewModel
import com.example.search_api.SearchFeatureApi

class SearchFeatureApiImpl(
    context: Context
) : SearchFeatureApi, NavigationFactory {
    private val di = context.inject(DependencyProvider::class.java)

    override val route: String = "search_graph"
    private val homeDestination = "search"

    @OptIn(ExperimentalSharedTransitionApi::class)
    override fun SharedTransitionScope.screen(
        builder: NavGraphBuilder,
        navController: NavHostController
    ) {
        builder.navigation(route = route, startDestination = homeDestination) {
            composable(homeDestination) {
                val searchViewModel: SearchViewModel = hiltViewModel()

                CompositionLocalProvider(
                    LocalAnimation provides AnimationParams("search"),
                ) {
                    SearchScreen(
                        camListViewModel = searchViewModel
                    )
                }
            }
        }
    }

    override fun screen(builder: NavGraphBuilder, navController: NavHostController) {

    }
}