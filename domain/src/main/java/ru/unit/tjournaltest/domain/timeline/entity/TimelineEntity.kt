package ru.unit.tjournaltest.domain.timeline.entity

data class TimelineEntity(
    val lastId: String,
    val lastSortingValue: String,
    val items: List<TimelineItemEntity>
)
