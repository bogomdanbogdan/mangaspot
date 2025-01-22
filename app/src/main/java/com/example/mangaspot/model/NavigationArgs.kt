package com.example.mangaspot.model

sealed class NavigationArgs(val startDestination: String) {

    data object SplashNavigation : NavigationArgs("")

    class StartNavigation(destination: String) : NavigationArgs(destination)

    class Navigation(startDestination: String, val navigation: String) :
        NavigationArgs(startDestination)

}