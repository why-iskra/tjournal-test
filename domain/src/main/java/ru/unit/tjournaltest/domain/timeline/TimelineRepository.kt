package ru.unit.tjournaltest.domain.timeline

import ru.unit.tjournaltest.domain.timeline.pojo.TimelinePOJO

interface TimelineRepository {
    suspend fun getRamCacheTimelineVideoAndGifs(lastId: String, lastSortingValue: String): TimelinePOJO?
    suspend fun putRamCacheTimelineVideoAndGifs(value: TimelinePOJO, lastId: String, lastSortingValue: String)
    suspend fun clearRamCacheTimelineVideoAndGifs()
}