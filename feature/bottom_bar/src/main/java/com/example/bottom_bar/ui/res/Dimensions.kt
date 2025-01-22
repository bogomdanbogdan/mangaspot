package com.example.bottom_bar.ui.res

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal val LocalDimen = compositionLocalOf { Dimensions() }

internal data class Dimensions(
    val barHeight: Dp = 56.dp,
    val barShadow: Dp = 6.dp,
    val iconSize: Dp = 22.dp,
    val iconBarBottomPadding: Dp = 12.dp,
    val textBarVerticalPadding: Dp = 6.dp,
)