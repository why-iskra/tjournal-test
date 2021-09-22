package ru.unit.tjournaltest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.api.dto.UserResponseDTO
import ru.unit.tjournaltest.repository.UserRepository

class AccountViewModel : ViewModel() {

    val userMeFlow = MutableStateFlow<UserResponseDTO?>(null)

    fun loadUserMe() {
        viewModelScope.launch(Dispatchers.IO) {
            userMeFlow.value = UserRepository.getUserMe()
        }
    }

    fun reset() {
        viewModelScope.launch(Dispatchers.IO) {
            UserRepository.reset()
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