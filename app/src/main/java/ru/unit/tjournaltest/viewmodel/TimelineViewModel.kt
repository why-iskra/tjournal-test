package ru.unit.tjournaltest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.domain.timeline.TimelineUseCase
import ru.unit.tjournaltest.domain.timeline.pojo.TimelineItemPOJO
import ru.unit.tjournaltest.paging.source.TimelinePagingSource
import javax.inject.Inject

@HiltViewModel
class TimelineViewModel @Inject constructor(
    private val timelineUseCase: TimelineUseCase
) : ViewModel() {

    val timelineItemsFlow: Flow<PagingData<TimelineItemPOJO>> = Pager(
        config = PagingConfig(pageSize = 10, prefetchDistance = 5),
        pagingSourceFactory = { TimelinePagingSource(timelineUseCase) }
    ).flow.cachedIn(viewModelScope)

    val preloadFlow = MutableStateFlow<List<TimelineItemPOJO>>(emptyList())

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                preloadFlow.value = timelineUseCase.getPreloadedVideoAndGifs().items
            }
        }
    }
}