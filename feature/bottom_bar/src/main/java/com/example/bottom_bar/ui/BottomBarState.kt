package com.example.bottom_bar.ui

import com.example.bottom_bar.tabs.Tab

data class BottomBarState(
    val selectedTab: Tab,
    val visible: Boolean = true
)