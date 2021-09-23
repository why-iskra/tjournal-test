package ru.unit.tjournaltest.data.converter

import ru.unit.tjournaltest.data.api.dto.*
import ru.unit.tjournaltest.domain.timeline.entity.*
import java.time.ZoneOffset

object TimelineConverter {
    fun apiResponseToEntity(value: TimelineResponseDTO)
        = TimelineEntity(value.lastId, value.lastSortingValue, value.items.map { item ->
            fun externalService(es: TimelineExternalServiceDTO) = TimelineExternalServiceEntity(
                es.id,
                es.name
            )

            fun text(text: TimelineTypeTextDTO?) = if(text != null) TimelineTypeTextEntity(text.text) else null

            fun image(image: TimelineTypeImageDTO?) = if(image != null) TimelineTypeImageEntity(
                image.type,
                image.uuid
            ) else null

            fun video(video: TimelineTypeVideoDTO?) = if(video != null) TimelineTypeVideoEntity(
                image(video.thumbnail) ?: TimelineTypeImageEntity("", ""),
                video.title,
                externalService(video.externalService)
            ) else null

            fun cover(cover: TimelineCoverDTO?) = if(cover != null) TimelineCoverEntity(
                cover.type,
                text(cover.text),
                video(cover.video),
                image(cover.image)
            ) else null

            TimelineItemEntity(
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