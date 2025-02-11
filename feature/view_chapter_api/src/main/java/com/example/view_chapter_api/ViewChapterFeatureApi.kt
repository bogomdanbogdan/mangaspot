package com.example.view_chapter_api

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface ViewChapterFeatureApi {
    val route: String
    val homeDestination: String

    fun screen(builder: NavGraphBuilder, navController: NavHostController)
}