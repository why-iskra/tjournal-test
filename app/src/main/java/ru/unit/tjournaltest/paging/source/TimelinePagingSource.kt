package ru.unit.tjournaltest.paging.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.unit.tjournaltest.api.dto.TimelineItemDTO
import ru.unit.tjournaltest.repository.Repository

class TimelinePagingSource(
    private val repository: Repository
) : PagingSource<Pair<String, String>, TimelineItemDTO>() {

    override fun getRefreshKey(state: PagingState<Pair<String, String>, TimelineItemDTO>): Pair<String, String>? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Pair<String, String>>): LoadResult<Pair<String, String>, TimelineItemDTO> {
        val key = params.key ?: Pair("", "")

        return try {
            val result = repository.timeline.getTimelineVideoAndGifs(key.first, key.second)

            LoadResult.Page(
                data = result.items,
                prevKey = params.key,
                nextKey = Pair(result.lastId, result.lastSortingValue)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
