package com.example.view_chapter.api

import android.content.Context
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.core.utils.di.inject
import com.example.core.utils.navigation.NavigationFactory
import com.example.view_chapter.di.DependencyProvider
import com.example.view_chapter.ui.ViewChapterScreen
import com.example.view_chapter.ui.ViewChapterViewModel
import com.example.view_chapter_api.ViewChapterFeatureApi

class ViewChapterFeatureApiImpl(
    context: Context
) : ViewChapterFeatureApi, NavigationFactory {
    private val di = context.inject(DependencyProvider::class.java)

    override val route: String = "view_chapter_graph"
    override val homeDestination = "view_chapter"

    override fun screen(
        builder: NavGraphBuilder,
        navController: NavHostController
    ) {
        builder.navigation(route = route, startDestination = homeDestination) {
            composable(homeDestination) {
                val viewChapterViewModel: ViewChapterViewModel = hiltViewModel()
                ViewChapterScreen(
                    viewChapterViewModel = viewChapterViewModel,
                    chapterNumber = "1",
                    mangaName = "Sakamoto Days",
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}