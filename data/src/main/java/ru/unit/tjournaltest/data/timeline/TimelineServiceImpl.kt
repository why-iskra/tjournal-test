package ru.unit.tjournaltest.data.timeline

import ru.unit.tjournaltest.data.api.TJournal
import ru.unit.tjournaltest.data.api.TJournalServiceV2
import ru.unit.tjournaltest.data.converter.TimelineConverter
import ru.unit.tjournaltest.domain.timeline.TimelineService
import javax.inject.Inject

class TimelineServiceImpl @Inject constructor(
    private val apiV2: TJournalServiceV2
) : TimelineService {

    override suspend fun getVideoAndGifs(lastId: String, lastSortingValue: String) =
        TimelineConverter.apiResponseToEntity(apiV2.videoAndGifs(TJournal.videoAndGifsId, "hotness", false, lastId, lastSortingValue))

}