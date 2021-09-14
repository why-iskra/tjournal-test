package ru.unit.tjournaltest.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.repository.Repository

class TimelineFragmentViewModel : ViewModel() {

    fun reset() {
        CoroutineScope(Dispatchers.IO).launch {
            Repository.reset()
        }
    }

}