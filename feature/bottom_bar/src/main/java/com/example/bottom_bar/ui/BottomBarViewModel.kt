package com.example.bottom_bar.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.bottom_bar.tabs.BottomTabs
import com.example.bottom_bar.tabs.Tab
import com.example.core.utils.scroll.ScreensWithList
import com.example.core.utils.scroll.ScrollToTopHandler
import com.example.library_api.LibraryFeatureApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomBarViewModel @Inject constructor(
    val bottomTabs: BottomTabs,
    private val scrollToTopHandler: ScrollToTopHandler,
    libraryFeatureApi: LibraryFeatureApi,
) : ViewModel() {

    private val routesToHide = listOf(
        libraryFeatureApi.route,
    )

    private val _state = MutableStateFlow(BottomBarState(selectedTab = bottomTabs.library))
    val state = _state.asStateFlow()

    private val _navigationEvents = MutableSharedFlow<Tab>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    private val _closeEvents = MutableSharedFlow<String>()
    val closeEvents = _closeEvents.asSharedFlow()

    fun onNavDestinationChanges(navDestination: NavDestination) {
        viewModelScope.launch {
            handleVisibility(navDestination)

            val selectedRoute = navDestination.hierarchy
                .map { it.route }
                .firstOrNull { route ->
                    route in bottomTabs.getTabs().map { it.route }
                }

            bottomTabs.getTabs().firstOrNull { it.route == selectedRoute }?.let {
                selectTab(it)
            }
        }
    }

    private fun handleVisibility(navDestination: NavDestination) {
        viewModelScope.launch {
            val visible = routesToHide.contains(navDestination.route).not()
            _state.update { it.copy(visible = visible) }
        }
    }

    private fun selectTab(tab: Tab) {
        viewModelScope.launch {
            _state.emit(state.value.copy(selectedTab = tab))
        }
    }

    fun onTabClick(tab: Tab, destination: NavDestination?) {
        viewModelScope.launch {

            if (tab == state.value.selectedTab) {
                scrollToTop(tab.route)
                return@launch
            }

            _navigationEvents.emit(tab)
            selectTab(tab)
        }
    }

    private suspend fun scrollToTop(route: String) {
        when (route) {
            bottomTabs.library.route -> scrollToTopHandler.scrollToTopOn(ScreensWithList.LIBRARY)
            bottomTabs.search.route -> scrollToTopHandler.scrollToTopOn(ScreensWithList.SEARCH)
            bottomTabs.settings.route -> scrollToTopHandler.scrollToTopOn(ScreensWithList.SETTINGS)
        }
    }
}