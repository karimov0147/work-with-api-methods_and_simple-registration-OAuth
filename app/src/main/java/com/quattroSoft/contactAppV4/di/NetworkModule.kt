package com.quattroSoft.contactAppV4.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.quattroSoft.contactAppV4.data.source.remote.ContactApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private val BASE_URL = "https://3b20-95-214-210-69.ap.ngrok.io"



    @[Provides Singleton]
    fun getRetrofit(client: OkHttpClient) :Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(BASE_URL)
        .build()






    @[Provides Singleton]
    fun client(@ApplicationContext context: Context) : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor.Builder(context).build())
        .build()


    @[Provides Singleton]
    fun provideApi(retrofit: Retrofit) : ContactApi = retrofit.create(ContactApi::class.java)


}