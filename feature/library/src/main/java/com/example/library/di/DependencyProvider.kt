package com.example.library.di

import com.example.search_api.SearchFeatureApi
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DependencyProvider {
    val searchFeatureApi: SearchFeatureApi
}