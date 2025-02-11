package com.example.view_chapter.utils

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalColors = compositionLocalOf { Colors() }

internal data class Colors(
    val elementBgc: Color = Color(0xFF0F0F0F),
)