package ru.unit.tjournaltest.domain.timeline.entity

data class TimelineCoverEntity(
    val type: String,
    val text: TimelineTypeTextEntity?,
    val video: TimelineTypeVideoEntity?,
    val image: TimelineTypeImageEntity?
)
