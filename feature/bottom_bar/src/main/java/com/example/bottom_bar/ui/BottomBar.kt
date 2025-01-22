package com.example.bottom_bar.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.bottom_bar.R
import com.example.bottom_bar.tabs.Tab
import com.example.bottom_bar.ui.res.LocalDimen
import com.example.core.utils.navigation.navigateSaved
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn

@Composable
fun BottomBar(
    navController: NavController,
    viewModel: BottomBarViewModel,
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(navBackStackEntry) {
        navBackStackEntry?.let {
            viewModel.onNavDestinationChanges(it.destination)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.navigationEvents
            .shareIn(this, SharingStarted.Lazily)
            .collect { tab ->
                navController.navigateSaved(tab.route)
            }
    }

    LaunchedEffect(Unit) {
        viewModel.closeEvents
            .shareIn(this, SharingStarted.Lazily)
            .collect { route ->
                navController.popBackStack(route, true)
            }
    }

    AnimatedContent(
        modifier = Modifier,
        targetState = state.visible,
        label = stringResource(id = R.string.bottom_bar_label)
    ) {
        if (it) {
            BottomBarContent(
                state = state,
                tabs = viewModel.bottomTabs.getTabs(),
                onTabClick = { tab ->
                    viewModel.onTabClick(tab, navController.currentDestination)
                },
            )
        }
    }
}

@Composable
internal fun BottomBarContent(
    state: BottomBarState,
    tabs: List<Tab>,
    modifier: Modifier = Modifier,
    onTabClick: (Tab) -> Unit,
) {
    val dimensions = LocalDimen.current
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensions.barHeight)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize(),
        ) {
            tabs.forEach { tab ->
                BottomTab(
                    tab = tab,
                    selected = state.selectedTab == tab,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    onClick = { onTabClick(tab) }
                )
            }
        }
    }
}