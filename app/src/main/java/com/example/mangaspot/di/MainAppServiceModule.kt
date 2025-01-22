package com.example.mangaspot.di

import android.content.Context
import com.example.core.utils.NavigationFactory
import com.example.library.api.LibraryFeatureApiImpl
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
    fun provideProfileNavigationFactory(@ApplicationContext context: Context): NavigationFactory {
        return LibraryFeatureApiImpl(context)
    }
}