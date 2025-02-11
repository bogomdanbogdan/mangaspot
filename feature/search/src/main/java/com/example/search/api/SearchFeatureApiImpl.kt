package com.example.search.api

import android.content.Context
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
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
    override val homeDestination = "search"

    override fun screen(
        builder: NavGraphBuilder,
        navController: NavHostController
    ) {
        builder.navigation(route = route, startDestination = homeDestination) {
            composable(homeDestination) {
                val searchViewModel: SearchViewModel = hiltViewModel()
                SearchScreen(camListViewModel = searchViewModel)
            }
        }
    }
}