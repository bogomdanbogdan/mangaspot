package com.example.manga_details_api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface MangaDetailsFeatureApi {
    val route: String
    val homeDestination: String

    fun screen(builder: NavGraphBuilder, navController: NavHostController)
}