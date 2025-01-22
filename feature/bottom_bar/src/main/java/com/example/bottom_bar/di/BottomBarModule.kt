package com.example.bottom_bar.di

import com.example.bottom_bar.tabs.BottomTabs
import com.example.bottom_bar.tabs.BottomTabsImpl
import com.example.library_api.LibraryFeatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class BottomBarModule {

    @Provides
    fun provideBottomTabs(
        libraryFeatureApi: LibraryFeatureApi,
    ): BottomTabs {
        return BottomTabsImpl(
            libraryFeatureApi,
        )
    }
}