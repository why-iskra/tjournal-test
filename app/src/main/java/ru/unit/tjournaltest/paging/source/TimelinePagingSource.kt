package ru.unit.tjournaltest.paging.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.unit.tjournaltest.domain.timeline.TimelineUseCase
import ru.unit.tjournaltest.domain.timeline.pojo.TimelineItemPOJO
import ru.unit.tjournaltest.domain.timeline.pojo.TimelinePOJO

class TimelinePagingSource(
    private val timelineUseCase: TimelineUseCase
) : PagingSource<PagingSourceKey, TimelineItemPOJO>() {

    override fun getRefreshKey(state: PagingState<PagingSourceKey, TimelineItemPOJO>): PagingSourceKey? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<PagingSourceKey>): LoadResult<PagingSourceKey, TimelineItemPOJO> {
        val key = params.key ?: PagingSourceKey("", "", 0)

        return try {
            lateinit var result: TimelinePOJO
            withContext(Dispatchers.IO) {
                result = timelineUseCase.getVideoAndGifs(key.lastId, key.lastSortingId, key.page)
            }

            LoadResult.Page(
                data = result.items,
                prevKey = params.key,
                nextKey = PagingSourceKey(result.lastId, result.lastSortingValue, key.page + 1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}

data class PagingSourceKey(
    val lastId: String,
    val lastSortingId: String,
    val page: Int
)
