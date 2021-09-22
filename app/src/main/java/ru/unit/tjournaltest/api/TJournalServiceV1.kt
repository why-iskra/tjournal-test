package ru.unit.tjournaltest.api

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import ru.unit.tjournaltest.api.dto.LoginResponseDTO
import ru.unit.tjournaltest.api.dto.UserResponseDTO

interface TJournalServiceV1 {
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("login") login: String,
        @Field("password") password: String
    ): LoginResponseDTO

    @GET("user/me")
    suspend fun userMe(): UserResponseDTO
}