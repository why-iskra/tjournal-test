package ru.unit.tjournaltest.data.timeline

import ru.unit.tjournaltest.data.api.TJournal
import ru.unit.tjournaltest.data.api.TJournalServiceV2
import ru.unit.tjournaltest.data.transformer.TimelineDtoToPojoTransformer
import ru.unit.tjournaltest.domain.timeline.TimelineService
import javax.inject.Inject

class TimelineServiceImpl @Inject constructor(
    private val apiV2: TJournalServiceV2,
    private val timelineDtoToPojoTransformer: TimelineDtoToPojoTransformer
) : TimelineService {

    override suspend fun getVideoAndGifs(lastId: String, lastSortingValue: String) =
        timelineDtoToPojoTransformer.transform(apiV2.videoAndGifs(TJournal.videoAndGifsId, "date", false, lastId, lastSortingValue))

}