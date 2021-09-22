package ru.unit.tjournaltest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.api.dto.UserResponseDTO
import ru.unit.tjournaltest.repository.Repository
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    val repository: Repository
) : ViewModel() {

    val userMeFlow = MutableStateFlow<UserResponseDTO?>(null)

    fun loadUserMe() {
        viewModelScope.launch(Dispatchers.IO) {
            userMeFlow.value = repository.user.getUserMe()
        }
    }

    fun reset() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.user.reset()
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            userMeFlow.value = null
            reset()
            loadUserMe()
        }
    }

}