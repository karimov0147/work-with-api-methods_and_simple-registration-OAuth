package com.quattroSoft.contactAppV4.di

import com.quattroSoft.contactAppV4.domain.repository.AuthRepository
import com.quattroSoft.contactAppV4.domain.repository.impl.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AuthRepositoryModule {

    @Binds
    @Singleton
    fun bindAuthRepository(impl : AuthRepositoryImpl) : AuthRepository

}