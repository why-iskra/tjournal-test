package ru.unit.tjournaltest.api.v2

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.unit.tjournaltest.api.dto.TimelineResponseDTO
import ru.unit.tjournaltest.api.interceptor.UserAgentInterceptor

class TJournalV2 {

    companion object {
        fun genImageRectUrl(uuid: String, s: Int) = "https://leonardo.osnova.io/%s/-/scale_crop/%dx%d/".format(uuid, s, s)
        fun genImageUrl(uuid: String) = "https://leonardo.osnova.io/%s/".format(uuid)
        fun genImageGifMP4Url(uuid: String) = "https://leonardo.osnova.io/%s/-/format/mp4/".format(uuid)
//        fun genYouTubeLink(uuid: String) = "https://youtube.com/watch?v=%s".format(uuid)

        private const val address = "https://api.tjournal.ru/v2.0/"
        private const val videoAndGifsId = 237832
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(UserAgentInterceptor())
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