package ru.unit.tjournaltest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.unit.tjournaltest.api.TJournal
import ru.unit.tjournaltest.api.TJournalServiceV1
import ru.unit.tjournaltest.api.TJournalServiceV2
import ru.unit.tjournaltest.api.interceptor.RequestInterceptor
import ru.unit.tjournaltest.api.interceptor.ResponseInterceptor

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideOkhttpClient(
        requestInterceptor: RequestInterceptor,
        responseInterceptor: ResponseInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(requestInterceptor)
        .addInterceptor(responseInterceptor)
        .build()

    @Provides
    fun provideRetrofitBuilder(
        client: OkHttpClient
    ): Retrofit.Builder = Retrofit.Builder().client(client).addConverterFactory(GsonConverterFactory.create())

    @Provides
    fun provideServiceApiV1(
        builder: Retrofit.Builder
    ): TJournalServiceV1 = builder.baseUrl(TJournal.addressApiV1).build().create(TJournalServiceV1::class.java)

    @Provides
    fun provideServiceApiV2(
        builder: Retrofit.Builder
    ): TJournalServiceV2 = builder.baseUrl(TJournal.addressApiV2).build().create(TJournalServiceV2::class.java)
}