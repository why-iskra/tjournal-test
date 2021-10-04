package ru.unit.tjournaltest.data.socket.dto

import com.google.gson.annotations.SerializedName

data class AuthorDTO(
    @SerializedName("title") val title: String?
)
