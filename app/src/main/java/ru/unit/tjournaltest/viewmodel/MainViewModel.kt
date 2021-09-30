package ru.unit.tjournaltest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.socket.client.Socket
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.unit.tjournaltest.data.socket.SocketState
import ru.unit.tjournaltest.data.socket.annotation.SocketEventFlow
import ru.unit.tjournaltest.data.socket.annotation.SocketStateFlow
import ru.unit.tjournaltest.data.socket.event.EventDTO
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val socket: Socket,
    @SocketEventFlow val socketEventFlow: MutableStateFlow<EventDTO?>,
    @SocketStateFlow val socketStateFlow: MutableStateFlow<SocketState>
) : ViewModel() {

    init {
        socketConnect()
    }

    fun getMessage(event: EventDTO?): Pair<String, String> {
        val title = event?.messengerEvent?.actionAddMessage?.author?.title ?: ""
        val text = event?.messengerEvent?.actionAddMessage?.text ?: ""
        return Pair(title, text)
    }

    private fun socketConnect() {
        viewModelScope.launch(Dispatchers.IO) {
            socket.connect()
        }
    }
}
