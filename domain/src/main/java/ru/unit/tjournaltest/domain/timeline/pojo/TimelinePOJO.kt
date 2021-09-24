package ru.unit.tjournaltest.domain.timeline.pojo

data class TimelinePOJO(
    val lastId: String,
    val lastSortingValue: String,
    val items: List<TimelineItemPOJO>
)
