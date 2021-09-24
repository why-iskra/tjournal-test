package ru.unit.tjournaltest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.data.sharedpreferences.SharedPreferencesAuth
import ru.unit.tjournaltest.domain.user.UserUseCase
import ru.unit.tjournaltest.domain.user.pojo.UserPOJO
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val preferencesAuth: SharedPreferencesAuth
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, t ->
        resultFlow.value = LoginError.INTERNAL
        t.printStackTrace()
    }

    val resultFlow = MutableStateFlow<LoginError?>(null)

    fun login(login: String, password: String) {
        resultFlow.value = null

        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            lateinit var result: UserPOJO
            runCatching {
                result = userUseCase.login(login, password)
            }.onSuccess {
                resultFlow.value = if (result.success) LoginError.NON else LoginError.UNKNOWN
            }.onFailure {
                it.printStackTrace()
                resultFlow.value = LoginError.UNAUTHORIZED
            }
        }
    }

    fun isAuthorized() = preferencesAuth.xDeviceToken.isNullOrEmpty()

    enum class LoginError {
        NON,
        INTERNAL,
        UNAUTHORIZED,
        UNKNOWN
    }
}
