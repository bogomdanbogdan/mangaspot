package com.example.view_chapter.di

import android.content.Context
import com.example.view_chapter.api.ViewChapterFeatureApiImpl
import com.example.view_chapter_api.ViewChapterFeatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MangaDetailsModule {

    @Provides
    fun provideViewChapterFeatureApi(@ApplicationContext context: Context): ViewChapterFeatureApi {
        return ViewChapterFeatureApiImpl(context)
    }

}