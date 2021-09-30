package ru.unit.tjournaltest.data.socket

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.EngineIOException
import io.socket.engineio.client.transports.WebSocket
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.OkHttpClient
import ru.unit.tjournaltest.data.json.annotation.GsonSimple
import ru.unit.tjournaltest.data.socket.annotation.SocketEventFlow
import ru.unit.tjournaltest.data.socket.annotation.SocketStateFlow
import ru.unit.tjournaltest.data.socket.event.EventDTO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SocketModule {

    @Singleton
    @Provides
    @SocketEventFlow
    fun provideSocketEventFlow(): MutableStateFlow<EventDTO?> = MutableStateFlow(null)

    @Singleton
    @Provides
    @SocketStateFlow
    fun provideSocketStateFlow(): MutableStateFlow<SocketState> = MutableStateFlow(SocketState.NOTHING)

    @Singleton
    @Provides
    fun provideSocket(
        okHttpClient: OkHttpClient,
        socketIOHelper: SocketHelper,
        @SocketEventFlow socketEventFlow: MutableStateFlow<EventDTO?>,
        @SocketStateFlow socketStateFlow: MutableStateFlow<SocketState>,
        @GsonSimple gson: Gson
    ): Socket {
        IO.setDefaultOkHttpWebSocketFactory(okHttpClient)
        val options = IO.Options().apply {
            transports = arrayOf(WebSocket.NAME)
        }
        val socket = IO.socket("https://ws-sio.tjournal.ru", options)

        socket.on(Socket.EVENT_CONNECT) {
            socketIOHelper.subscribeToChannels(socket)
            socketStateFlow.value = SocketState.CONNECTED
        }.on(Socket.EVENT_CONNECT_ERROR) {
            val ex = it.first()
            if (ex is EngineIOException) {
                ex.printStackTrace()
            }
            socketStateFlow.value = SocketState.CONNECTION_ERROR
        }.on(Socket.EVENT_DISCONNECT) {
            socketStateFlow.value = SocketState.DISCONNECTED
        }.on("event") {
            runCatching {
                socketStateFlow.value = SocketState.EVENT
                socketEventFlow.value = gson.fromJson(it.first().toString(), EventDTO::class.java)
            }.onFailure {
                it.printStackTrace()
                socketStateFlow.value = SocketState.EVENT_ERROR
                socketEventFlow.value = null
            }
        }

        return socket
    }
}