package ru.unit.tjournaltest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.domain.user.UserUseCase
import ru.unit.tjournaltest.domain.user.entity.UserEntity
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    val userMeFlow = MutableStateFlow<UserEntity?>(null)

    fun loadUserMe() {
        viewModelScope.launch(Dispatchers.IO) {
            userMeFlow.value = userUseCase.getUserMe()
        }
    }

    fun reset() {
        viewModelScope.launch(Dispatchers.IO) {
            userUseCase.clearCache()
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