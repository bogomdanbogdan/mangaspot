package com.example.library_api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface LibraryFeatureApi {
    val route: String
    val homeDestination: String

    fun screen(builder: NavGraphBuilder, navController: NavHostController)
}