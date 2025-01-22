package com.example.bottom_bar.ui.res

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalColors = compositionLocalOf { Colors() }

internal data class Colors(
    val activeIconColor: Color = Color(0xFF0061F3),
    val inactiveIconColor: Color = Color(0xFFC2C9D4)
)