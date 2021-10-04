package ru.unit.tjournaltest.data.socket.dto

import com.google.gson.annotations.SerializedName

data class EventDTO(
    @SerializedName("message") val message: MessageDTO?,
    @SerializedName("action") val action: String?
)
