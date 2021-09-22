package ru.unit.tjournaltest.repository

import ru.unit.barsdiary.other.CacheFunction

object TimelineRepository {

    private val cachedTimelineVideoAndGifsArgs = CacheFunction {
        RepositoryApiController.instance.apiV2.videoAndGifsRequest(it[0].toString(), it[1].toString())
    }

    private val cachedTimelineVideoAndGifs = CacheFunction {
        RepositoryApiController.instance.apiV2.videoAndGifsRequest()
    }

    suspend fun getTimelineVideoAndGifs(lastId: String, lastSortingValue: String) = cachedTimelineVideoAndGifsArgs(lastId, lastSortingValue)
    suspend fun getTimelineVideoAndGifs() = cachedTimelineVideoAndGifs()

    suspend fun reset() {
        cachedTimelineVideoAndGifs.reset()
        cachedTimelineVideoAndGifsArgs.reset()
    }
}