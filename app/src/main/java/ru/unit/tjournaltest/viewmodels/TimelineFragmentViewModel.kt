package ru.unit.tjournaltest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.unit.tjournaltest.repository.Repository

class TimelineFragmentViewModel : ViewModel() {

    fun reset() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Repository.reset()
            }
        }
    }

}