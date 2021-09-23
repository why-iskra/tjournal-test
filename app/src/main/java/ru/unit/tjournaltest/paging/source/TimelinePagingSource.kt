package ru.unit.tjournaltest.paging.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.unit.tjournaltest.domain.timeline.TimelineUseCase
import ru.unit.tjournaltest.domain.timeline.entity.TimelineItemEntity

class TimelinePagingSource(
    private val timelineUseCase: TimelineUseCase
) : PagingSource<Pair<String, String>, TimelineItemEntity>() {

    override fun getRefreshKey(state: PagingState<Pair<String, String>, TimelineItemEntity>): Pair<String, String>? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Pair<String, String>>): LoadResult<Pair<String, String>, TimelineItemEntity> {
        val key = params.key ?: Pair("", "")

        return try {
            val result = timelineUseCase.getVideoAndGifs(key.first, key.second)

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
