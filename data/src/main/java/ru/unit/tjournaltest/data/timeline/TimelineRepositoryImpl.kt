package ru.unit.tjournaltest.data.timeline

import ru.unit.tjournaltest.data.other.RamCache
import ru.unit.tjournaltest.domain.timeline.TimelineRepository
import ru.unit.tjournaltest.domain.timeline.pojo.TimelinePOJO
import javax.inject.Inject

class TimelineRepositoryImpl @Inject constructor() : TimelineRepository {

    private val cacheVideoAndGifsArgs = RamCache<TimelinePOJO>()

    override suspend fun getRamCacheTimelineVideoAndGifs(lastId: String, lastSortingValue: String) =
        cacheVideoAndGifsArgs.get(lastId, lastSortingValue)

    override suspend fun putRamCacheTimelineVideoAndGifs(value: TimelinePOJO, lastId: String, lastSortingValue: String) {
        cacheVideoAndGifsArgs.put(value, lastId, lastSortingValue)
    }

    override suspend fun clearRamCacheTimelineVideoAndGifs() {
        cacheVideoAndGifsArgs.clear()
    }

}