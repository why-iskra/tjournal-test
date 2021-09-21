package ru.unit.tjournaltest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import ru.unit.tjournaltest.repository.RepositoryApiController

class LoginViewModel : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, t ->
        resultFlow.value = LoginError.INTERNAL
        t.printStackTrace()
    }

    val resultFlow = MutableStateFlow<LoginError?>(null)

    fun login(login: String, password: String) {
        resultFlow.value = null

        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            try {
                val result = RepositoryApiController.instance.login(login, password)
                resultFlow.value = if (result.success) LoginError.NON else LoginError.UNKNOWN
            } catch (e: HttpException) {
                resultFlow.value = if (e.code() == 400) LoginError.UNAUTHORIZED else LoginError.UNKNOWN
            }
        }
    }

    enum class LoginError {
        NON,
        INTERNAL,
        UNAUTHORIZED,
        UNKNOWN
    }

    data class LoginResult(
        val error: LoginError
    )
}
