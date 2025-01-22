package com.example.search.di

import android.content.Context
import com.example.search.api.SearchFeatureApiImpl
import com.example.search_api.SearchFeatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SearchModule {

    @Provides
    fun provideSearchFeatureApi(@ApplicationContext context: Context): SearchFeatureApi {
        return SearchFeatureApiImpl(context)
    }

}