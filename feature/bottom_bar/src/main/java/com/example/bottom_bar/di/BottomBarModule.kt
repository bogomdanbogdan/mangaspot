package com.example.bottom_bar.di

import com.example.bottom_bar.tabs.BottomTabs
import com.example.bottom_bar.tabs.BottomTabsImpl
import com.example.library_api.LibraryFeatureApi
import com.example.search_api.SearchFeatureApi
import com.example.settings_api.SettingsFeatureApi
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
        searchFeatureApi: SearchFeatureApi,
        settingsFeatureApi: SettingsFeatureApi
    ): BottomTabs {
        return BottomTabsImpl(
            libraryFeatureApi,
            searchFeatureApi,
            settingsFeatureApi
        )
    }
}