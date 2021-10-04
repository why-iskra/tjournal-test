package ru.unit.tjournaltest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.domain.user.UserUseCase
import ru.unit.tjournaltest.domain.user.pojo.UserPOJO
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val userUseCase: UserUseCase
) : ViewModel() {

    val userMeFlow = MutableStateFlow<UserPOJO?>(null)
    val stateFlow = MutableStateFlow<State?>(null)

    fun loadUserMe() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                stateFlow.value = State.LOADING

                userMeFlow.value = userUseCase.getUserMe()

                stateFlow.value = State.SUCCESS
            }.onFailure {
                stateFlow.value = State.FAIL
            }
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                stateFlow.value = State.LOADING

                userUseCase.clearUserMe()
                userMeFlow.value = userUseCase.getUserMe()

                stateFlow.value = State.SUCCESS
            }.onFailure {
                stateFlow.value = State.FAIL
            }
        }
    }

    fun isAuthorized() = userUseCase.isAuthorized()

    fun logout() {
        userUseCase.logout()
    }

    enum class State {
        NOTHING,
        LOADING,
        SUCCESS,
        FAIL
    }
}