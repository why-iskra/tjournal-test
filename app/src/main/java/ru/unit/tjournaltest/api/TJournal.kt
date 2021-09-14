package ru.unit.tjournaltest.api

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.unit.tjournaltest.api.dto.TimelineResponseDTO

class TJournal {

    companion object {
        fun genImageRectUrl(uuid: String, s: Int) = "https://leonardo.osnova.io/%s/-/scale_crop/%dx%d/".format(uuid, s, s)
        fun genImageUrl(uuid: String) = "https://leonardo.osnova.io/%s/".format(uuid)
        fun genImageGifMP4Url(uuid: String) = "https://leonardo.osnova.io/%s/-/format/mp4/".format(uuid)
        fun genYouTubeLink(uuid: String) = "https://youtube.com/watch?v=%s".format(uuid)

        private const val address = "https://api.tjournal.ru/v2.0"
        private const val videoAndGifsId = 237832
    }

    private val client = OkHttpClient.Builder().addInterceptor {
        return@addInterceptor it.run {
            proceed(
                request()
                    .newBuilder()
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", "Android V1")
                    .build()
            )
        }
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(address)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(TJournalService::class.java)

    fun videoAndGifsRequest(): TimelineResponseDTO? =
        service.videoAndGifs(videoAndGifsId.toString(), "hotness", false).execute().body()

    fun videoAndGifsRequest(lastId: String, lastSortingValue: String): TimelineResponseDTO? =
        service.videoAndGifs(videoAndGifsId.toString(), "hotness", false, lastId, lastSortingValue).execute().body()

    interface TJournalService {
        @GET("timeline")
        fun videoAndGifs(
            @Query("subsitesIds") subsitesIds: String,
            @Query("sorting") sorting: String,
            @Query("allSite") allSite: Boolean
        ): Call<TimelineResponseDTO>

        @GET("timeline")
        fun videoAndGifs(
            @Query("subsitesIds") subsitesIds: String,
            @Query("sorting") sorting: String,
            @Query("allSite") allSite: Boolean,
            @Query("lastId") lastId: String,
            @Query("lastSortingValue") lastSortingValue: String
        ): Call<TimelineResponseDTO>
    }
}