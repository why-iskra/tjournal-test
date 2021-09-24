package ru.unit.tjournaltest.data.api.dto

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.ZoneOffset


@JsonAdapter(TimelineResponseDTODeserializer::class)
data class TimelineResponseDTO(
    val lastId: String,
    val lastSortingValue: String,
    val items: List<TimelineItemDTO>
)

data class TimelineItemDTO(
    val id: Long,

    val subsiteName: String,
    val avatar: TimelineTypeImageDTO,
    val authorName: String,
    val title: String,
    val date: LocalDateTime,
    val comments: Long,
    val rating: Long,

    val cover: TimelineCoverDTO?
)

data class TimelineCoverDTO(
    val type: String,
    val text: TimelineTypeTextDTO?,
    val video: TimelineTypeVideoDTO?,
    val image: TimelineTypeImageDTO?
)

data class TimelineTypeTextDTO(
    val text: String
)

data class TimelineTypeVideoDTO(
    val thumbnail: TimelineTypeImageDTO,
    val title: String,
    val externalService: TimelineExternalServiceDTO
)

data class TimelineTypeImageDTO(
    val type: String,
    val uuid: String
)

data class TimelineExternalServiceDTO(
    val name: String,
    val id: String
)

class TimelineResponseDTODeserializer : JsonDeserializer<TimelineResponseDTO> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): TimelineResponseDTO {
        if (json == null) return TimelineResponseDTO("", "", emptyList())

        val obj = json.asJsonObject.getAsJsonObject("result")

        val lastId = obj.getAsJsonPrimitive("lastId").asString
        val lastSortingValue = obj.getAsJsonPrimitive("lastSortingValue").asString
        val items = obj.getAsJsonArray("items").map(::deserializeItem).toList()

        return TimelineResponseDTO(lastId, lastSortingValue, items)
    }

    fun deserializeItem(elem: JsonElement): TimelineItemDTO {
        val obj = elem.asJsonObject.getAsJsonObject("data")

        val subsiteName: String
        val avatar: TimelineTypeImageDTO
        with(obj.getAsJsonObject("subsite")) {
            subsiteName = getAsJsonPrimitive("name").asString
            avatar = deserializeTypeImage(this.getAsJsonObject("avatar"))
        }

        val authorName = obj.getAsJsonObject("author")
            .getAsJsonPrimitive("name").asString
        val date = LocalDateTime.ofEpochSecond(obj.getAsJsonPrimitive("date").asLong, 0, ZoneOffset.UTC)
        val title = obj.getAsJsonPrimitive("title").asString
        val comments = obj.getAsJsonObject("counters")
            .getAsJsonPrimitive("comments").asLong
        val rating = obj.getAsJsonObject("likes")
            .getAsJsonPrimitive("counter").asLong
        val id = obj.getAsJsonPrimitive("id").asLong

        val rawCover = obj.getAsJsonArray("blocks").filter {
            val block = it.asJsonObject
            return@filter block.getAsJsonPrimitive("cover").asBoolean && !block.getAsJsonPrimitive("hidden").asBoolean
        }.singleOrNull()

        var cover: TimelineCoverDTO? = null
        if (rawCover != null) {
            cover = deserializeCover(rawCover)
        }

        return TimelineItemDTO(id, subsiteName, avatar, authorName, title, date, comments, rating, cover)
    }

    fun deserializeCover(elem: JsonElement): TimelineCoverDTO {
        val obj = elem.asJsonObject

        val type = if (obj.has("type")) {
            obj.getAsJsonPrimitive("type").asString
        } else {
            "image"
        }

        var video: TimelineTypeVideoDTO? = null
        var text: TimelineTypeTextDTO? = null
        var image: TimelineTypeImageDTO? = null

        when (type) {
            "video" -> video = deserializeTypeVideo(obj.getAsJsonObject("data"))
            "text" -> text = deserializeTypeText(obj.getAsJsonObject("data"))
            "media" -> image = deserializeTypeImage(
                obj.getAsJsonObject("data")
                    .getAsJsonArray("items")
                    .get(0)
                    .asJsonObject
                    .getAsJsonObject("image")
            )
            "image" -> image = deserializeTypeImage(
                obj.getAsJsonArray("items")
                    .get(0)
                    .asJsonObject
                    .getAsJsonObject("image")
            )
        }

        return TimelineCoverDTO(type, text, video, image)
    }

    fun deserializeTypeImage(elem: JsonElement): TimelineTypeImageDTO {
        val obj = elem.asJsonObject

        val type: String
        val uuid: String
        with(obj.getAsJsonObject("data")) {
            type = getAsJsonPrimitive("type").asString
            uuid = getAsJsonPrimitive("uuid").asString
        }

        return TimelineTypeImageDTO(type, uuid)
    }

    fun deserializeTypeVideo(elem: JsonElement): TimelineTypeVideoDTO {
        val obj = elem.asJsonObject

        val video = obj
            .getAsJsonObject("video")
            .getAsJsonObject("data")

        val thumbnail = deserializeTypeImage(video.get("thumbnail"))
        val externalService = deserializeExternalService(video.get("external_service"))
        val title = obj.getAsJsonPrimitive("title").asString

        return TimelineTypeVideoDTO(thumbnail, title, externalService)
    }

    fun deserializeTypeText(elem: JsonElement): TimelineTypeTextDTO {
        return TimelineTypeTextDTO(
            elem.asJsonObject.getAsJsonPrimitive("text").asString
        )
    }

    fun deserializeExternalService(elem: JsonElement): TimelineExternalServiceDTO {
        val obj = elem.asJsonObject

        val name = obj.getAsJsonPrimitive("name").asString
        val id = obj.getAsJsonPrimitive("id").asString

        return TimelineExternalServiceDTO(name, id)
    }
}