package ru.unit.tjournaltest.domain.timeline

import ru.unit.tjournaltest.domain.timeline.entity.TimelineEntity

interface TimelineRepository {
    suspend fun getRamCacheTimelineVideoAndGifs(lastId: String, lastSortingValue: String): TimelineEntity?
    suspend fun putRamCacheTimelineVideoAndGifs(value: TimelineEntity, lastId: String, lastSortingValue: String)
    suspend fun clearRamCacheTimelineVideoAndGifs()
}