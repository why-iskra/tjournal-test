package ru.unit.tjournaltest.domain.timeline.pojo

data class TimelineTypeVideoPOJO(
    val thumbnail: TimelineTypeImagePOJO?,
    val title: String?,
    val externalService: TimelineExternalServicePOJO
)
