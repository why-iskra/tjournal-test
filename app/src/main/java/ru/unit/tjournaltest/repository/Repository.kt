package ru.unit.tjournaltest.repository

import ru.unit.barsdiary.other.CacheFunction
import ru.unit.tjournaltest.api.TJournal

object Repository {

    private val api = TJournal()

    private val cachedTimelineVideoAndGifsArgs = CacheFunction {
        api.videoAndGifsRequest(it[0].toString(), it[1].toString())
    }

    private val cachedTimelineVideoAndGifs = CacheFunction {
        api.videoAndGifsRequest()
    }

    suspend fun getTimelineVideoAndGifs(lastId: String, lastSortingValue: String) = cachedTimelineVideoAndGifsArgs(lastId, lastSortingValue)
    suspend fun getTimelineVideoAndGifs() = cachedTimelineVideoAndGifs()

    suspend fun reset() {
        cachedTimelineVideoAndGifs.reset()
        cachedTimelineVideoAndGifsArgs.reset()
    }
}