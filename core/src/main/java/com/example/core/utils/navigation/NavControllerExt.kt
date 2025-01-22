package com.example.core.utils.navigation

import androidx.annotation.MainThread
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions

@MainThread
fun NavController.navigateSingle(route: String, builder: (NavOptionsBuilder.() -> Unit)? = null) {
    navigate(route, navOptions {
        launchSingleTop = true
        builder?.invoke(this)
    })
}

@MainThread
fun NavController.navigateInclusive(destination: String) {
    this.navigate(destination) {
        val currentRoute = this@navigateInclusive.currentDestination?.route ?: return@navigate
        popUpTo(currentRoute) { inclusive = true }
    }
}

@MainThread
fun NavController.navigateToAndClose(destination: String) {
    navigateUp()
    navigate(destination)
}

@MainThread
fun NavController.safePopBackStack(): Boolean {
    return if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        popBackStack()
    } else false
}

@MainThread
fun NavController.navigateSaved(destination: String) {
    this.navigate(destination) {
        popUpTo(this@navigateSaved.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}