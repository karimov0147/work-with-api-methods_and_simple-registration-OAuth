package com.quattroSoft.contactAppV4.di

import android.content.Context
import android.content.SharedPreferences
import com.quattroSoft.contactAppV4.data.source.local.LocalStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalStorageModule {
    @[Provides Singleton]
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences = context.getSharedPreferences("LC" , Context.MODE_PRIVATE)

    @[Provides Singleton]
    fun provideLocalStorage(preferences: SharedPreferences) = LocalStorage(preferences)

}