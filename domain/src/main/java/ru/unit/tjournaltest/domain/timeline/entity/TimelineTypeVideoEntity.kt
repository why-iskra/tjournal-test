package ru.unit.tjournaltest.domain.timeline.entity

data class TimelineTypeVideoEntity(
    val thumbnail: TimelineTypeImageEntity,
    val title: String,
    val externalService: TimelineExternalServiceEntity
)
