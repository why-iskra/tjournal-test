package ru.unit.tjournaltest.domain.timeline

import ru.unit.tjournaltest.domain.timeline.pojo.TimelinePOJO

interface TimelineService {
    suspend fun getSubsite(lastId: String, lastSortingValue: String): TimelinePOJO
}