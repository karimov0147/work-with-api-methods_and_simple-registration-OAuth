package com.quattroSoft.contactAppV4.di

import android.content.Context
import com.quattroSoft.contactAppV4.data.source.local.AppDataBase
import com.quattroSoft.contactAppV4.data.source.local.dao.ContactDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule  {

    @Provides
    fun providesContactDatabase(@ApplicationContext context: Context) : AppDataBase = AppDataBase.init(context)

    @Provides
    fun providesContactDao(appDataBase: AppDataBase) : ContactDao = appDataBase.getContactDao()

}