package com.example.bottom_bar.tabs

interface BottomTabs {

    val library: Tab
    val search: Tab
    val settings: Tab
    fun getTabs(): List<Tab>
}
