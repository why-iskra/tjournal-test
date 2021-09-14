package ru.unit.tjournaltest.datasource

import androidx.paging.PositionalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.api.dto.TimelineItemDTO
import ru.unit.tjournaltest.repository.Repository

class TimelineDataSource : PositionalDataSource<TimelineItemDTO>() {
    private var lastId: String = ""
    private var lastSortingValue: String = ""

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<TimelineItemDTO>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = Repository.getTimelineVideoAndGifs()

                if (result != null) {
                    lastId = result.lastId
                    lastSortingValue = result.lastSortingValue

                    callback.onResult(result.items, 0)
                } else {
                    callback.onResult(emptyList(), 0)
                }
            } catch (e: Exception) {
                // ignore
            }
        }
    }

    override fun loadRange(
        params: LoadRangeParams,
        callback: LoadRangeCallback<TimelineItemDTO>
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = Repository.getTimelineVideoAndGifs(lastId, lastSortingValue)

                if (result != null) {
                    lastId = result.lastId
                    lastSortingValue = result.lastSortingValue

                    callback.onResult(result.items)
                } else {
                    callback.onResult(emptyList())
                }
            } catch (e: Exception) {
                // ignore
            }
        }
    }
}