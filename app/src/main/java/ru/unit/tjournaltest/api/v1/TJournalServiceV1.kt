package ru.unit.tjournaltest.api.v1

import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import ru.unit.tjournaltest.api.dto.LoginResponseDTO
import ru.unit.tjournaltest.api.dto.UserResponseDTO

interface TJournalServiceV1 {
    @Multipart
    @POST("auth/login")
    suspend fun login(
        @Part("login") login: RequestBody,
        @Part("password") password: RequestBody
    ): LoginResponseDTO

    @GET("user/me")
    suspend fun userMe(): UserResponseDTO
}