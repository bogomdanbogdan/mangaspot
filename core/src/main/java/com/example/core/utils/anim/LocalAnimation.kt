package com.example.core.utils.anim

import androidx.compose.runtime.compositionLocalOf

val LocalAnimation = compositionLocalOf { AnimationParams() }

data class AnimationParams(
    val featureTag: String = "",
)