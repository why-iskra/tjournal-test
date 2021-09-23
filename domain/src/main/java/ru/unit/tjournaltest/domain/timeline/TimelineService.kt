package ru.unit.tjournaltest.domain.timeline

import ru.unit.tjournaltest.domain.timeline.entity.TimelineEntity

interface TimelineService {
    suspend fun getVideoAndGifs(lastId: String, lastSortingValue: String): TimelineEntity
}