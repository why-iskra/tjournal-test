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
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.api.dto.TimelineItemDTO
import ru.unit.tjournaltest.paging.source.TimelinePagingSource
import ru.unit.tjournaltest.repository.Repository
import javax.inject.Inject

@HiltViewModel
class TimelineViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    val timelineItemsFlow: Flow<PagingData<TimelineItemDTO>> = Pager(
        config = PagingConfig(pageSize = 10, prefetchDistance = 5),
        pagingSourceFactory = { TimelinePagingSource(repository) }
    ).flow.cachedIn(viewModelScope)

    fun reset() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.timeline.reset()
        }
    }

}