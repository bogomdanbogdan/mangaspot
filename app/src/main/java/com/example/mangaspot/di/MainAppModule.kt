package com.example.mangaspot.di

import com.example.mangaspot.helper.NotificationCommandIntentProvider
import com.example.mangaspot.helper.NotificationCommandIntentProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MainAppModule {
    @Provides
    fun provideNotificationCommandIntentProviderImpl(): NotificationCommandIntentProvider {
        return NotificationCommandIntentProviderImpl()
    }
}