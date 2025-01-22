package com.example.settings.di

import android.content.Context
import com.example.settings.api.SettingsFeatureApiImpl
import com.example.settings_api.SettingsFeatureApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class SettingsModule {

    @Provides
    fun provideSettingsFeatureApi(@ApplicationContext context: Context): SettingsFeatureApi {
        return SettingsFeatureApiImpl(context)
    }

}