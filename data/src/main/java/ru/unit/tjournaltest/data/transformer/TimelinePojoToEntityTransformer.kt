package ru.unit.tjournaltest.data.transformer

import ru.unit.tjournaltest.data.room.entity.*
import ru.unit.tjournaltest.domain.timeline.pojo.*
import javax.inject.Inject

class TimelinePojoToEntityTransformer @Inject constructor() : RevertTransformer<TimelineItemPOJO, Timeline> {
    override fun transform(value: TimelineItemPOJO): Timeline {
        fun externalService(es: TimelineExternalServicePOJO) = TimelineExternalService(
            es.id,
            es.name
        )

        fun text(text: TimelineTypeTextPOJO?) = if (text != null) TimelineTypeText(text.text) else null

        fun image(image: TimelineTypeImagePOJO?) = if (image != null) TimelineTypeImage(
            image.type,
            image.uuid
        ) else null

        fun video(video: TimelineTypeVideoPOJO?) = if (video != null) TimelineTypeVideo(
            image(video.thumbnail),
            video.title,
            externalService(video.externalService)
        ) else null

        fun cover(cover: TimelineCoverPOJO?) = if (cover != null) TimelineCover(
            cover.type,
            text(cover.text),
            video(cover.video),
            image(cover.image)
        ) else null

        return Timeline(
            value.id,
            value.subsiteName,
            image(value.avatar),
            value.authorName,
            value.title,
            value.date,
            value.comments,
            value.rating,
            cover(value.cover)
        )
    }

    override fun revert(value: Timeline): TimelineItemPOJO {
        fun externalService(es: TimelineExternalService) =
            if (es.idExternalService != null && es.name != null)
                TimelineExternalServicePOJO(es.idExternalService, es.name)
            else null

        fun text(text: TimelineTypeText?) =
            if (text?.text != null) TimelineTypeTextPOJO(text.text) else null

        fun image(image: TimelineTypeImage?) =
            if (image?.typeImage != null && image.uuid != null) TimelineTypeImagePOJO(
                image.typeImage,
                image.uuid
            ) else null

        fun video(video: TimelineTypeVideo?): TimelineTypeVideoPOJO? {
            val es = if (video?.externalService != null) externalService(video.externalService) else null

            return if (video?.title != null && es != null) TimelineTypeVideoPOJO(
                image(video.thumbnail),
                video.title,
                es
            ) else null
        }

        fun cover(cover: TimelineCover?) = if (cover?.type != null) TimelineCoverPOJO(
            cover.type,
            text(cover.text),
            video(cover.video),
            image(cover.image)
        ) else null

        return TimelineItemPOJO(
            value.id,
            value.subsiteName,
            image(value.avatar),
            value.authorName,
            value.title,
            value.date,
            value.comments,
            value.rating,
            cover(value.cover)
        )
    }

    fun revertToList(list: List<Timeline>): TimelinePOJO {
        return TimelinePOJO("", "", list.map {
            revert(it)
        }.toList())
    }
}