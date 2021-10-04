package ru.unit.tjournaltest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.socket.client.Socket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.data.notification.MessageNotification
import ru.unit.tjournaltest.data.socket.SocketState
import ru.unit.tjournaltest.data.socket.annotation.SocketEventFlow
import ru.unit.tjournaltest.data.socket.annotation.SocketStateFlow
import ru.unit.tjournaltest.data.socket.dto.ChannelDTO
import ru.unit.tjournaltest.domain.user.UserUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val socket: Socket,
    @SocketEventFlow private val socketEventFlow: MutableStateFlow<ChannelDTO?>,
    @SocketStateFlow val socketStateFlow: MutableStateFlow<SocketState>,
    private val messageNotification: MessageNotification
) : ViewModel() {

    init {
        socketInit()
    }

    private fun socketInit() {
        viewModelScope.launch {
            socketEventFlow.collect {
                val action = it?.data?.action
                val title = it?.data?.message?.author?.title
                val text = it?.data?.message?.text

                if (!text.isNullOrEmpty() && !title.isNullOrEmpty() && action == "addMessage") {
                    messageNotification.createNotification(title, text)
                }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            socket.connect()
        }
    }

    fun isAuthorized() = userUseCase.isAuthorized()
}
