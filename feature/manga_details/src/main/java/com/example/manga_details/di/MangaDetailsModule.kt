package com.example.manga_details.di

import android.content.Context
import com.example.manga_details.api.MangaDetailsFeatureApiImpl
import com.example.manga_details_api.MangaDetailsFeatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MangaDetailsModule {

    @Provides
    fun provideLMangaDetailsFeatureApi(@ApplicationContext context: Context): MangaDetailsFeatureApi {
        return MangaDetailsFeatureApiImpl(context)
    }

}