package com.quattroSoft.contactAppV4.di

import android.content.Context
import com.quattroSoft.contactAppV4.utills.AppConnectionUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {

    @[Provides Singleton]
    fun providesConnectionUtils(@ApplicationContext context: Context) = AppConnectionUtil(context)
}

