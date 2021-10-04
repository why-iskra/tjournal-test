package ru.unit.tjournaltest.data.socket

import android.util.Log
import io.socket.client.Socket
import org.json.JSONObject

private const val SUBSCRIBE_EVENT = "subscribe"

fun Socket.subscribeToChannels(messengerHash: String?) {
    val channels = listOfNotNull(
        messengerHash?.let { "m:$it" }
    )

    channels.forEach {
        emit(SUBSCRIBE_EVENT, getChannelData(it))
        Log.d("socketio", "subscribe to channel '$it'")
    }
}

private fun getChannelData(channelName: String): JSONObject {
    return JSONObject().apply {
        put("channel", channelName)
    }
}
