package ru.unit.tjournaltest.domain.timeline

import ru.unit.tjournaltest.domain.timeline.pojo.TimelinePOJO

interface TimelineService {
    suspend fun getVideoAndGifs(lastId: String, lastSortingValue: String): TimelinePOJO
}