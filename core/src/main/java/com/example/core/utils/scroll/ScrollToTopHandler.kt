package com.example.core.utils.scroll

import kotlinx.coroutines.flow.Flow

interface ScrollToTopHandler {
    suspend fun scrollToTopOn(screen: ScreensWithList)
    fun getScrollState(): Flow<ScreensWithList>
}