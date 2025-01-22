package com.example.search_api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface SearchFeatureApi {
    val route: String

    fun screen(builder: NavGraphBuilder, navController: NavHostController)
}