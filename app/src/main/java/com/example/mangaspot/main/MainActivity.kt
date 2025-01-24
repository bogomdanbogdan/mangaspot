package com.example.mangaspot.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bottom_bar.ui.BottomBar
import com.example.core.utils.dispatchers.AppDispatchers
import com.example.core.utils.navigation.NavigationFactory
import com.example.core.utils.navigation.navigateSingle
import com.example.core.utils.theme.MangaSpotTheme
import com.example.library_api.LibraryFeatureApi
import com.example.manga_details_api.MangaDetailsFeatureApi
import com.example.mangaspot.main.nav_graph.AppNavGraph
import com.example.mangaspot.model.NavigationArgs
import com.example.search_api.SearchFeatureApi
import com.example.settings_api.SettingsFeatureApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationFactories: @JvmSuppressWildcards Set<NavigationFactory>

    @Inject
    lateinit var libraryFeatureApi: LibraryFeatureApi

    @Inject
    lateinit var searchFeatureApi: SearchFeatureApi

    @Inject
    lateinit var settingsFeatureApi: SettingsFeatureApi

    @Inject
    lateinit var mangaDetailsFeatureApi: MangaDetailsFeatureApi

    private lateinit var viewModel: MainViewModel

    private var keepSplashScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            keepSplashScreen
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            window.decorView.setBackgroundColor(MaterialTheme.colorScheme.background.value.toInt())
            viewModel = hiltViewModel()

            val state by viewModel.state.collectAsStateWithLifecycle()
            val navController = rememberNavController()

            MangaSpotTheme(context = LocalContext.current, currentTheme = state.currentTheme) {
                LaunchedEffect(Unit) {
                    withContext(AppDispatchers.IO) {
                        keepSplashScreen = false
                    }
                }

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it)
                            .imePadding()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.TopCenter)
                        ) {

                            val currentBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = currentBackStackEntry?.destination?.route

                            AppNavGraph(
                                navController = navController,
                                navigationFactories = navigationFactories,
                                startDestination = libraryFeatureApi.route,
                                modifier = Modifier.weight(1f),
                                libraryFeatureApi = libraryFeatureApi,
                                searchFeatureApi = searchFeatureApi,
                                settingsFeatureApi = settingsFeatureApi,
                                mangaDetailsFeatureApi = mangaDetailsFeatureApi
                            )

                            AnimatedVisibility(
                                visible = currentRoute != mangaDetailsFeatureApi.homeDestination,
                                enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                                exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
                            ) {
                                BottomBar(
                                    navController = navController,
                                    viewModel = hiltViewModel(),
                                )
                            }

                            if (currentRoute == mangaDetailsFeatureApi.homeDestination) {
                                window.navigationBarColor = colorScheme.background.toArgb()
                            } else {
                                window.navigationBarColor = colorScheme.surface.toArgb()
                            }
                        }
                    }
                }

                HandleCommand(
                    navigation = state.navigation,
                    navController = navController
                )
            }
        }
    }
}

@Composable
private fun HandleCommand(
    navigation: NavigationArgs,
    navController: NavHostController
) {
    when (navigation) {
        is NavigationArgs.Navigation -> {
            navController.navigateUp()
            navController.navigateSingle(navigation.navigation)
        }

        else -> return
    }
}
