package ru.unit.tjournaltest.domain.timeline.pojo

data class TimelineCoverPOJO(
    val type: String?,
    val text: TimelineTypeTextPOJO?,
    val video: TimelineTypeVideoPOJO?,
    val image: TimelineTypeImagePOJO?
)
