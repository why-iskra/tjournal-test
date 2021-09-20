package ru.unit.tjournaltest.api.v1

import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.unit.tjournaltest.api.dto.LoginResponseDTO
import ru.unit.tjournaltest.api.dto.UserResponseDTO
import ru.unit.tjournaltest.api.interceptor.UserAgentInterceptor
import ru.unit.tjournaltest.api.interceptor.XDeviceTokenCatcherInterceptor

class TJournalV1 {

    companion object {
        fun genImageUrl(uuid: String) = "https://leonardo.osnova.io/%s/".format(uuid)
        private const val address = "https://api.tjournal.ru/v1.8/"
    }

    private val xDeviceTokenInterceptor = XDeviceTokenCatcherInterceptor()

    private val client = OkHttpClient.Builder()
        .addInterceptor(UserAgentInterceptor())
        .addInterceptor(xDeviceTokenInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(address)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(TJournalServiceV1::class.java)

    suspend fun login(login: String, password: String): LoginResponseDTO =
        service.login(
            RequestBody.create(MediaType.parse("form-data"), login),
            RequestBody.create(MediaType.parse("form-data"), password)
        )

    suspend fun userMeRequest(): UserResponseDTO = service.userMe()

    fun getXDeviceToken() = xDeviceTokenInterceptor.XDeviceToken
    fun setXDeviceToken(token: String) {
        xDeviceTokenInterceptor.XDeviceToken = token
    }
}