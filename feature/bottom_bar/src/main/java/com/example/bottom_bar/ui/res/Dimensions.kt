package com.example.bottom_bar.ui.res

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal val LocalDimen = compositionLocalOf { Dimensions() }

internal data class Dimensions(
    val barHeight: Dp = 60.dp,
    val iconTextPadding: Dp = 4.dp,
    val iconSize: Dp = 26.dp,
)