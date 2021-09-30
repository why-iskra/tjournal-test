package ru.unit.tjournaltest.data.socket

import android.util.Log
import com.google.gson.Gson
import io.socket.client.Socket
import org.json.JSONObject
import ru.unit.tjournaltest.data.json.annotation.GsonSimple
import ru.unit.tjournaltest.data.sharedpreferences.SharedPreferencesUser
import javax.inject.Inject

class SocketHelper @Inject constructor(
    private val userPreferences: SharedPreferencesUser,
    @GsonSimple private val gson: Gson
) {

    companion object {
        private const val SUBSCRIBE_EVENT = "subscribe"
        private const val UNSUBSCRIBE_EVENT = "unsubscribe"
    }

    private val messengerHash
        get() = userPreferences.userMe?.result?.mHash

    private fun createChannelSubscriptions(): MutableList<String> {
        return listOfNotNull(
            messengerHash?.let { "m:$it" },
        ).toMutableList()
    }

    private val channelSubscriptions = createChannelSubscriptions()

    fun subscribeToChannels(socket: Socket) {
        channelSubscriptions.forEach {
            socket.emit(SUBSCRIBE_EVENT, getChannelData(it))
            Log.d("socketio", "subscribe to channel '$it'")
        }
    }

    fun unsubscribeToChannels(socket: Socket) {
        channelSubscriptions.forEach {
            socket.emit(UNSUBSCRIBE_EVENT, getChannelData(it))
            Log.d("socketio", "unsubscribe to channel '$it'")
        }
    }

    private fun getChannelData(channelName: String): JSONObject {
        return JSONObject().apply {
            put("channel", channelName)
        }
    }

}