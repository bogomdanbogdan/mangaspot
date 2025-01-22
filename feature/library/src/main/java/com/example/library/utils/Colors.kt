package com.example.library.utils

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

internal val LocalColors = compositionLocalOf { Colors() }

internal data class Colors(
    val totalChapters: Color = Color(0xFFDAE9FF),
    val userChapters: Color = Color(0xFF2DAF08)
)