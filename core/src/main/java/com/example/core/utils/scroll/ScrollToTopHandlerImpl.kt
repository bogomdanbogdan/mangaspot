package com.example.core.utils.scroll

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class ScrollToTopHandlerImpl : ScrollToTopHandler {

    private val currentScrollScreen = MutableSharedFlow<ScreensWithList>()

    override suspend fun scrollToTopOn(screen: ScreensWithList) {
        currentScrollScreen.emit(screen)
    }

    override fun getScrollState(): Flow<ScreensWithList> {
        return currentScrollScreen
    }

}