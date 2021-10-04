package ru.unit.tjournaltest.data.socket.dto

import com.google.gson.annotations.SerializedName

data class MessageDTO(
    @SerializedName("author") val author: AuthorDTO,
    @SerializedName("text") val text: String?
)
