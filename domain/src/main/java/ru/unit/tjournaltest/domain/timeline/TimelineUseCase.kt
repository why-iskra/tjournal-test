package ru.unit.tjournaltest.domain.timeline

import ru.unit.tjournaltest.domain.timeline.pojo.TimelinePOJO
import javax.inject.Inject

interface TimelineUseCase {
    suspend fun getVideoAndGifs(lastId: String, lastSortingValue: String, page: Int): TimelinePOJO
    suspend fun clearVideoAndGifs()
}

class TimelineUseCaseImpl @Inject constructor(
    private val timelineRepository: TimelineRepository,
    private val timelineService: TimelineService
) : TimelineUseCase {
    override suspend fun getVideoAndGifs(lastId: String, lastSortingValue: String, page: Int): TimelinePOJO {
        val repoResult = timelineRepository.getTimeline(page)
        return if (repoResult.items.isNotEmpty()) {
            repoResult
        } else {
            val apiResult = timelineService.getVideoAndGifs(lastId, lastSortingValue)
            timelineRepository.putTimeline(apiResult)
            apiResult
        }
    }

    override suspend fun clearVideoAndGifs() {
        timelineRepository.clearTimeline()
    }

}
