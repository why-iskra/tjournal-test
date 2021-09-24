package ru.unit.tjournaltest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.data.sharedpreferences.SharedPreferencesAuth
import ru.unit.tjournaltest.domain.user.UserUseCase
import ru.unit.tjournaltest.domain.user.pojo.UserPOJO
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val preferencesAuth: SharedPreferencesAuth
) : ViewModel() {

    val userMeFlow = MutableStateFlow<UserPOJO?>(null)

    fun loadUserMe() {
        viewModelScope.launch(Dispatchers.IO) {
            userMeFlow.value = userUseCase.getUserMe()
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            userMeFlow.value = null
            userUseCase.clearUserMe()
            userMeFlow.value = userUseCase.getUserMe()
        }
    }

    fun isAuthorized() = preferencesAuth.xDeviceToken.isNullOrEmpty()
}