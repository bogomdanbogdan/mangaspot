package com.example.library.di

import android.content.Context
import com.example.library.api.LibraryFeatureApiImpl
import com.example.library_api.LibraryFeatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LibraryModule {

    @Provides
    fun provideLibraryFeatureApi(@ApplicationContext context: Context): LibraryFeatureApi {
        return LibraryFeatureApiImpl(context)
    }

}