package com.example.manga_details.api

import android.content.Context
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.core.utils.di.inject
import com.example.core.utils.navigation.NavigationFactory
import com.example.manga_details.di.DependencyProvider
import com.example.manga_details.ui.MangaDetailsScreen
import com.example.manga_details.ui.MangaDetailsViewModel
import com.example.manga_details_api.MangaDetailsFeatureApi

class MangaDetailsFeatureApiImpl(
    context: Context
) : MangaDetailsFeatureApi, NavigationFactory {
    private val di = context.inject(DependencyProvider::class.java)

    override val route: String = "manga_details_graph"
    override val homeDestination = "manga_details"

    override fun screen(
        builder: NavGraphBuilder,
        navController: NavHostController
    ) {
        builder.navigation(route = route, startDestination = homeDestination) {
            composable(homeDestination) {
                val mangaDetailsViewModel: MangaDetailsViewModel = hiltViewModel()
                MangaDetailsScreen(
                    mangaDetailsViewModel = mangaDetailsViewModel,
                    onBackClick = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}