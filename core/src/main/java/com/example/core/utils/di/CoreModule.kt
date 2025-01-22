package com.example.core.utils.di

import com.example.core.utils.scroll.ScrollToTopHandler
import com.example.core.utils.scroll.ScrollToTopHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {

    @Provides
    @Singleton
    fun provideScrollToTopHandler(): ScrollToTopHandler {
        return ScrollToTopHandlerImpl()
    }

}