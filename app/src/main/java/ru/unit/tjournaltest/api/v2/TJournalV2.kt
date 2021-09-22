package ru.unit.tjournaltest.api.v2

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.unit.tjournaltest.api.dto.TimelineResponseDTO
import ru.unit.tjournaltest.api.interceptor.RequestInterceptor

class TJournalV2 {

    companion object {
        private const val address = "https://api.tjournal.ru/v2.0/"
        private const val videoAndGifsId = 237832
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(RequestInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(address)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(TJournalServiceV2::class.java)

    suspend fun videoAndGifsRequest(): TimelineResponseDTO =
        service.videoAndGifs(videoAndGifsId.toString(), "hotness", false)

    suspend fun videoAndGifsRequest(lastId: String, lastSortingValue: String): TimelineResponseDTO =
        service.videoAndGifs(videoAndGifsId.toString(), "hotness", false, lastId, lastSortingValue)

}