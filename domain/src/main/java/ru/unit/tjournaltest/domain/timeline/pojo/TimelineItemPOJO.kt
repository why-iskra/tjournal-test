package ru.unit.tjournaltest.domain.timeline.pojo

data class TimelineItemPOJO(
    val id: Long,

    val subsiteName: String,
    val avatar: TimelineTypeImagePOJO?,
    val authorName: String,
    val title: String,
    val date: Long,
    val comments: Long,
    val rating: Long,

    val cover: TimelineCoverPOJO?
)
