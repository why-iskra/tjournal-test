package ru.unit.tjournaltest.domain.timeline

import ru.unit.tjournaltest.domain.timeline.pojo.TimelinePOJO

interface TimelineRepository {
    suspend fun getTimeline(page: Int): TimelinePOJO
    suspend fun putTimeline(value: TimelinePOJO)
    suspend fun clearTimeline()
}