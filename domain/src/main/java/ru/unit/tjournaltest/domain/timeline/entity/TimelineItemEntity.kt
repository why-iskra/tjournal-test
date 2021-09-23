package ru.unit.tjournaltest.domain.timeline.entity

data class TimelineItemEntity(
    val id: Long,

    val subsiteName: String,
    val avatar: TimelineTypeImageEntity?,
    val authorName: String,
    val title: String,
    val date: Long,
    val comments: Long,
    val rating: Long,

    val cover: TimelineCoverEntity?
)
