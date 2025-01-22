package com.example.core.utils.di

import android.content.Context
import dagger.hilt.android.EntryPointAccessors

fun <T> Context.inject(dependencyProvider: Class<T>): T {
    return EntryPointAccessors.fromApplication(
        this,
        dependencyProvider
    )
}