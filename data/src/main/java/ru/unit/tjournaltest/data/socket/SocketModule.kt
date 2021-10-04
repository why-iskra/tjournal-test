package ru.unit.tjournaltest.data.socket

import android.util.Log
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
import ru.unit.tjournaltest.data.DataConfig
import ru.unit.tjournaltest.data.json.annotation.GsonSimple
import ru.unit.tjournaltest.data.sharedpreferences.SharedPreferencesUser
import ru.unit.tjournaltest.data.socket.annotation.SocketEventFlow
import ru.unit.tjournaltest.data.socket.annotation.SocketStateFlow
import ru.unit.tjournaltest.data.socket.dto.ChannelDTO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SocketModule {

    @Singleton
    @Provides
    @SocketEventFlow
    fun provideSocketEventFlow(): MutableStateFlow<ChannelDTO?> = MutableStateFlow(null)

    @Singleton
    @Provides
    @SocketStateFlow
    fun provideSocketStateFlow(): MutableStateFlow<SocketState> = MutableStateFlow(SocketState.NOTHING)

    @Singleton
    @Provides
    fun provideSocket(
        dataConfig: DataConfig,
        okHttpClient: OkHttpClient,
        userPreferences: SharedPreferencesUser,
        @SocketEventFlow socketEventFlow: MutableStateFlow<ChannelDTO?>,
        @SocketStateFlow socketStateFlow: MutableStateFlow<SocketState>,
        @GsonSimple gson: Gson
    ): Socket {
        IO.setDefaultOkHttpWebSocketFactory(okHttpClient)
        val options = IO.Options().apply {
            transports = arrayOf(WebSocket.NAME)
        }
        val socket = IO.socket(dataConfig.baseSocketHost, options)

        socket.on(Socket.EVENT_CONNECT) {
            socket.subscribeToChannels(userPreferences.userMe?.result?.mHash)
            socketStateFlow.value = SocketState.CONNECTED
        }.on(Socket.EVENT_CONNECT_ERROR) {
            Log.d("dbg", it.joinToString(" "))
            val ex = it.first()
            (ex as? EngineIOException)?.printStackTrace()

            socketStateFlow.value = SocketState.CONNECTION_ERROR
        }.on(Socket.EVENT_DISCONNECT) {
            socketStateFlow.value = SocketState.DISCONNECTED
        }.on("event") {
            runCatching {
                socketStateFlow.value = SocketState.EVENT
                socketEventFlow.value = gson.fromJson(it.first().toString(), ChannelDTO::class.java)
            }.onFailure {
                it.printStackTrace()
                socketStateFlow.value = SocketState.EVENT_ERROR
                socketEventFlow.value = null
            }
        }

        return socket
    }
}