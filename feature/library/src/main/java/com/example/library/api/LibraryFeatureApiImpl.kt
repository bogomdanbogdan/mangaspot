package com.example.library.api

import android.content.Context
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.core.utils.di.inject
import com.example.core.utils.navigation.NavigationFactory
import com.example.core.utils.navigation.navigateSingle
import com.example.library.di.DependencyProvider
import com.example.library.ui.CamListViewModel
import com.example.library.ui.LibraryScreen
import com.example.library_api.LibraryFeatureApi

class LibraryFeatureApiImpl(
    context: Context
) : LibraryFeatureApi, NavigationFactory {
    private val di = context.inject(DependencyProvider::class.java)

    override val route: String = "library_graph"
    private val homeDestination = "library"

    override fun screen(
        builder: NavGraphBuilder,
        navController: NavHostController
    ) {
        builder.navigation(route = route, startDestination = homeDestination) {
            composable(homeDestination) {
                val camListViewModel: CamListViewModel = hiltViewModel()
                LibraryScreen(
                    camListViewModel = camListViewModel,
                    onSearchPlaceholderClick = {
                        navController.navigate(di.searchFeatureApi.route)
                    },
                    onMangaSelected = {
                        navController.navigateSingle(di.mangaDetailsApi.route)
                    }
                )
            }
        }
    }
}