package com.example.bottom_bar.tabs

import com.example.bottom_bar.R
import com.example.library_api.LibraryFeatureApi

class BottomTabsImpl(
    private val libraryFeatureApi: LibraryFeatureApi,
) : BottomTabs {

    override val library = object : Tab {
        override val title = R.string.library_tab_name
        override val iconActive = R.drawable.ic_library_tab
        override val route = libraryFeatureApi.route
    }

    override val search = object : Tab {
        override val title = R.string.search_tab_name
        override val iconActive = R.drawable.ic_search_tab
        override val route = "camsListFeatureApi.route"
    }

    override val settings = object : Tab {
        override val title = R.string.settings_tab_name
        override val iconActive = R.drawable.ic_settings_tab
        override val route = "galleryFeatureApi.route"
    }

    override fun getTabs() = listOf(
        library,
        search,
        settings
    )
}