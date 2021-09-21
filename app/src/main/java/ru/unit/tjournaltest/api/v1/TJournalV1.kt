package ru.unit.tjournaltest.api.v1

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.unit.tjournaltest.api.dto.LoginResponseDTO
import ru.unit.tjournaltest.api.dto.UserResponseDTO
import ru.unit.tjournaltest.api.interceptor.RequestInterceptor
import ru.unit.tjournaltest.api.interceptor.ResponseInterceptor

class TJournalV1 {

    companion object {
        private const val address = "https://api.tjournal.ru/v1.8/"
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(RequestInterceptor())
        .addInterceptor(ResponseInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(address)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(TJournalServiceV1::class.java)

    suspend fun login(login: String, password: String): LoginResponseDTO = service.login(login, password)

    suspend fun userMeRequest(): UserResponseDTO = service.userMe()

}