package com.example.bottom_bar.tabs

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface Tab {
    @get:StringRes
    val title: Int

    @get:DrawableRes
    val iconActive: Int
    val route: String
}