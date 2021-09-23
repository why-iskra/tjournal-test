package ru.unit.tjournaltest.domain.timeline

import ru.unit.tjournaltest.domain.timeline.entity.TimelineEntity
import javax.inject.Inject

interface TimelineUseCase {
    suspend fun getVideoAndGifs(lastId: String, lastSortingValue: String): TimelineEntity
    suspend fun clearCache()
}

class TimelineUseCaseImpl @Inject constructor(
    private val timelineRepository: TimelineRepository,
    private val timelineService: TimelineService
) : TimelineUseCase {
    override suspend fun getVideoAndGifs(lastId: String, lastSortingValue: String): TimelineEntity {
        val cacheResult = timelineRepository.getRamCacheTimelineVideoAndGifs(lastId, lastSortingValue)
        if (cacheResult != null) {
            return cacheResult
        }

        val apiResult = timelineService.getVideoAndGifs(lastId, lastSortingValue)
        timelineRepository.putRamCacheTimelineVideoAndGifs(apiResult, lastId, lastSortingValue)

        return apiResult
    }

    override suspend fun clearCache() {
        timelineRepository.clearRamCacheTimelineVideoAndGifs()
    }

}
