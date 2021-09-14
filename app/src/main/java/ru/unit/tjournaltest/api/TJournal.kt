package ru.unit.tjournaltest.api

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
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

    private val client = HttpClient(Android) {
        expectSuccess = true
        install(JsonFeature) {
            serializer = GsonSerializer {
                setPrettyPrinting()
            }
            accept(ContentType.Application.Json)
            accept(ContentType.Text.Html)
        }
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.INFO
        }
    }

    suspend fun videoAndGifsRequest(): TimelineResponseDTO {
        return client.submitForm("$address/timeline", Parameters.build {
            append("subsitesIds", videoAndGifsId.toString())
            append("sorting", "hotness")
            append("allSite", "false")
        }, true)
    }

    suspend fun videoAndGifsRequest(lastId: String, lastSortingValue: String): TimelineResponseDTO {
        return client.submitForm("$address/timeline", Parameters.build {
            append("subsitesIds", videoAndGifsId.toString())
            append("sorting", "hotness")
            append("allSite", "false")
            append("lastId", lastId)
            append("lastSortingValue", lastSortingValue)
        }, true)
    }
}