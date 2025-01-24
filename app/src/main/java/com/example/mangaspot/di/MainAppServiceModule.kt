package com.example.mangaspot.di

import android.content.Context
import com.example.core.utils.navigation.NavigationFactory
import com.example.library.api.LibraryFeatureApiImpl
import com.example.manga_details.api.MangaDetailsFeatureApiImpl
import com.example.search.api.SearchFeatureApiImpl
import com.example.settings.api.SettingsFeatureApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet


@Module
@InstallIn(SingletonComponent::class)
class MainAppServiceModule {

    @Provides
    @IntoSet
    fun provideLibraryNavigationFactory(@ApplicationContext context: Context): NavigationFactory {
        return LibraryFeatureApiImpl(context)
    }

    @Provides
    @IntoSet
    fun provideSearchNavigationFactory(@ApplicationContext context: Context): NavigationFactory {
        return SearchFeatureApiImpl(context)
    }

    @Provides
    @IntoSet
    fun provideSettingsNavigationFactory(@ApplicationContext context: Context): NavigationFactory {
        return SettingsFeatureApiImpl(context)
    }

    @Provides
    fun provideMangaDetailsFeatureApi(@ApplicationContext context: Context): NavigationFactory {
        return MangaDetailsFeatureApiImpl(context)
    }
}