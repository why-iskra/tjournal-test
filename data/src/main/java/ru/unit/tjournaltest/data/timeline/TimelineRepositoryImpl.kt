package ru.unit.tjournaltest.data.timeline

import ru.unit.tjournaltest.data.room.DatabaseVar
import ru.unit.tjournaltest.data.transformer.TimelinePojoToEntityTransformer
import ru.unit.tjournaltest.domain.timeline.TimelineRepository
import ru.unit.tjournaltest.domain.timeline.pojo.TimelinePOJO
import javax.inject.Inject

class TimelineRepositoryImpl @Inject constructor(
    private val room: DatabaseVar,
    private val timelinePojoToEntityTransformer: TimelinePojoToEntityTransformer
) : TimelineRepository {

    override suspend fun getTimeline(page: Int): TimelinePOJO {
        return timelinePojoToEntityTransformer.revertToList(room.database.timelineDao().getPage(page))
    }

    override suspend fun putTimeline(value: TimelinePOJO) {
        val dao = room.database.timelineDao()
        dao.insertAll(value.items.map {
            timelinePojoToEntityTransformer.transform(it)
        }.toList())
    }

    override suspend fun clearTimeline() {
        room.database.timelineDao().deleteAll()
    }

}