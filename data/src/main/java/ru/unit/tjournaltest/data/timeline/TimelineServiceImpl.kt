package ru.unit.tjournaltest.data.timeline

import ru.unit.tjournaltest.data.api.ApiConfig
import ru.unit.tjournaltest.data.api.ApiServiceV2
import ru.unit.tjournaltest.data.transformer.TimelineDtoToPojoTransformer
import ru.unit.tjournaltest.domain.timeline.TimelineService
import javax.inject.Inject

class TimelineServiceImpl @Inject constructor(
    private val apiV2: ApiServiceV2,
    private val timelineDtoToPojoTransformer: TimelineDtoToPojoTransformer,
    private val apiConfig: ApiConfig
) : TimelineService {

    override suspend fun getSubsite(lastId: String, lastSortingValue: String) =
        timelineDtoToPojoTransformer.transform(apiV2.videoAndGifs(apiConfig.subsiteId.toString(), "date", false, lastId, lastSortingValue))

}