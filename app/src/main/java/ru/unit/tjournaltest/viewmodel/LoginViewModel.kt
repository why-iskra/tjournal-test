package ru.unit.tjournaltest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.domain.user.UserUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        stateFlow.value = State.ERROR_INTERNAL
    }

    val stateFlow = MutableStateFlow(State.NOTHING)

    fun login(login: String, password: String) {
        stateFlow.value = State.LOADING

        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            runCatching {
                val result = userUseCase.login(login, password)
                if (result == null) {
                    stateFlow.value = State.ERROR_UNAUTHORIZED
                } else {
                    stateFlow.value = if (result.success) State.LOADED else State.ERROR_UNKNOWN
                }
            }.onFailure {
                it.printStackTrace()
                stateFlow.value = State.ERROR_UNAUTHORIZED
            }
        }
    }

    fun isAuthorized() = userUseCase.isAuthorized()

    enum class State {
        NOTHING,
        LOADING,
        LOADED,
        ERROR_INTERNAL,
        ERROR_UNAUTHORIZED,
        ERROR_UNKNOWN
    }
}
