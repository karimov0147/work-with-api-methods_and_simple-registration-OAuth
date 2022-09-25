package com.quattroSoft.contactAppV4.di

import com.quattroSoft.contactAppV4.domain.repository.MainRepository
import com.quattroSoft.contactAppV4.domain.repository.impl.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MainRepositoryModule{

    @Binds
    @Singleton
    fun bindMainRepository(impl : MainRepositoryImpl) : MainRepository
}