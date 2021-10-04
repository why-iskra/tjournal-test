package ru.unit.tjournaltest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.unit.tjournaltest.BuildConfig
import ru.unit.tjournaltest.data.api.ApiConfig
import ru.unit.tjournaltest.data.api.ApiServiceV1
import ru.unit.tjournaltest.data.api.ApiServiceV2
import ru.unit.tjournaltest.data.api.interceptor.RequestInterceptor
import ru.unit.tjournaltest.data.api.interceptor.ResponseInterceptor

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideOkhttpClient(
        requestInterceptor: RequestInterceptor,
        responseInterceptor: ResponseInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        .addInterceptor(requestInterceptor)
        .addInterceptor(responseInterceptor)
        .build()

    @Provides
    fun provideRetrofitBuilder(
        client: OkHttpClient
    ): Retrofit.Builder = Retrofit.Builder().client(client).addConverterFactory(GsonConverterFactory.create())

    @Provides
    fun provideServiceApiV1(
        builder: Retrofit.Builder,
        apiConfig: ApiConfig
    ): ApiServiceV1 = builder.baseUrl(apiConfig.addressApiV1).build().create(ApiServiceV1::class.java)

    @Provides
    fun provideServiceApiV2(
        builder: Retrofit.Builder,
        apiConfig: ApiConfig
    ): ApiServiceV2 = builder.baseUrl(apiConfig.addressApiV2).build().create(ApiServiceV2::class.java)
}