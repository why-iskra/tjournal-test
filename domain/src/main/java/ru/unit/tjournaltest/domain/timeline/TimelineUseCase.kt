package ru.unit.tjournaltest.domain.timeline

import ru.unit.tjournaltest.domain.timeline.pojo.TimelinePOJO
import javax.inject.Inject

interface TimelineUseCase {
    suspend fun getVideoAndGifs(lastId: String, lastSortingValue: String): TimelinePOJO
    suspend fun putVideoAndGifs(value: TimelinePOJO)
    suspend fun getPreloadedVideoAndGifs(): TimelinePOJO
}

class TimelineUseCaseImpl @Inject constructor(
    private val timelineRepository: TimelineRepository,
    private val timelineService: TimelineService
) : TimelineUseCase {
    override suspend fun getVideoAndGifs(lastId: String, lastSortingValue: String): TimelinePOJO {
        val apiResult = timelineService.getVideoAndGifs(lastId, lastSortingValue)

        return apiResult
    }

    override suspend fun putVideoAndGifs(value: TimelinePOJO) {
        timelineRepository.putTimeline(value)
    }

    override suspend fun getPreloadedVideoAndGifs(): TimelinePOJO {
        return timelineRepository.getTimeline()
    }

}
