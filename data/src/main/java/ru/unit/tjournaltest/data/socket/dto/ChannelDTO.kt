package ru.unit.tjournaltest.data.socket.dto

import com.google.gson.annotations.SerializedName

data class ChannelDTO(
    @SerializedName("data") val data: EventDTO?
)
