package com.example.bottom_bar.tabs

import com.example.bottom_bar.R
import com.example.library_api.LibraryFeatureApi
import com.example.search_api.SearchFeatureApi
import com.example.settings_api.SettingsFeatureApi

class BottomTabsImpl(
    private val libraryFeatureApi: LibraryFeatureApi,
    private val searchFeatureApi: SearchFeatureApi,
    private val settingsFeatureApi: SettingsFeatureApi
) : BottomTabs {

    override val library = object : Tab {
        override val title = R.string.library_tab_name
        override val iconActive = R.drawable.library_tab
        override val route = libraryFeatureApi.route
    }

    override val search = object : Tab {
        override val title = R.string.search_tab_name
        override val iconActive = R.drawable.search_tab
        override val route = searchFeatureApi.route
    }

    override val settings = object : Tab {
        override val title = R.string.settings_tab_name
        override val iconActive = R.drawable.settings_tab
        override val route = settingsFeatureApi.route
    }

    override fun getTabs() = listOf(
        library,
        search,
        settings
    )
}