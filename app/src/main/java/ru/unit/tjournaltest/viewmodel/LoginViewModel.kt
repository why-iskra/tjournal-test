package ru.unit.tjournaltest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.domain.auth.AuthUseCase
import ru.unit.tjournaltest.domain.auth.entity.AuthEntity
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, t ->
        resultFlow.value = LoginError.INTERNAL
        t.printStackTrace()
    }

    val resultFlow = MutableStateFlow<LoginError?>(null)

    fun login(login: String, password: String) {
        resultFlow.value = null

        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            lateinit var result: AuthEntity
            runCatching{
                result = authUseCase.login(login, password)
            }.onSuccess {
                resultFlow.value = if (result.success) LoginError.NON else LoginError.UNKNOWN
            }.onFailure {
                resultFlow.value = LoginError.UNAUTHORIZED
            }
        }
    }

    enum class LoginError {
        NON,
        INTERNAL,
        UNAUTHORIZED,
        UNKNOWN
    }
}
