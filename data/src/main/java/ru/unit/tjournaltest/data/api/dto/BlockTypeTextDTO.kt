package ru.unit.tjournaltest.data.api.dto

import com.google.gson.annotations.SerializedName

data class BlockTypeTextDTO(
    @SerializedName("text") val text: String?
) : BlockTypeBaseDTO()
