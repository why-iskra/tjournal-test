package ru.unit.tjournaltest.data.transformer

import ru.unit.tjournaltest.data.api.dto.*
import ru.unit.tjournaltest.domain.timeline.pojo.*
import java.time.ZoneOffset
import javax.inject.Inject

class TimelineDtoTransformer @Inject constructor() : BaseTransformer<TimelineResponseDTO, TimelinePOJO> {
    override fun transform(value: TimelineResponseDTO) = TimelinePOJO(value.lastId, value.lastSortingValue, value.items.map { item ->
        fun externalService(es: TimelineExternalServiceDTO) = TimelineExternalServicePOJO(
            es.id,
            es.name
        )

        fun text(text: TimelineTypeTextDTO?) = if (text != null) TimelineTypeTextPOJO(text.text) else null

        fun image(image: TimelineTypeImageDTO?) = if (image != null) TimelineTypeImagePOJO(
            image.type,
            image.uuid
        ) else null

        fun video(video: TimelineTypeVideoDTO?) = if (video != null) TimelineTypeVideoPOJO(
            image(video.thumbnail) ?: TimelineTypeImagePOJO("", ""),
            video.title,
            externalService(video.externalService)
        ) else null

        fun cover(cover: TimelineCoverDTO?) = if (cover != null) TimelineCoverPOJO(
            cover.type,
            text(cover.text),
            video(cover.video),
            image(cover.image)
        ) else null

        TimelineItemPOJO(
            item.id,
            item.subsiteName,
            image(item.avatar),
            item.authorName,
            item.title,
            item.date.toEpochSecond(ZoneOffset.UTC),
            item.comments,
            item.rating,
            cover(item.cover)
        )
    }.toList())
}