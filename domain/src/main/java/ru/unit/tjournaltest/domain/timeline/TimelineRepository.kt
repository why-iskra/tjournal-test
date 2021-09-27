package ru.unit.tjournaltest.domain.timeline

import ru.unit.tjournaltest.domain.timeline.pojo.TimelinePOJO

interface TimelineRepository {
    suspend fun getTimeline(): TimelinePOJO
    suspend fun putTimeline(value: TimelinePOJO)
}