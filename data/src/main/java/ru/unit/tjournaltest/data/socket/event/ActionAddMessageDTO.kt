package ru.unit.tjournaltest.data.socket.event

import com.google.gson.annotations.SerializedName

data class ActionAddMessageDTO(
    @SerializedName("text") val text: String?,
    @SerializedName("author") val author: ActionAddMessageAuthorDTO?
)

data class ActionAddMessageAuthorDTO(
    @SerializedName("title") val title: String?
)
