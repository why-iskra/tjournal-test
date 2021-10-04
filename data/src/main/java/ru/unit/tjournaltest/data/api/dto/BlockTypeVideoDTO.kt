package ru.unit.tjournaltest.data.api.dto

import com.google.gson.annotations.SerializedName

data class BlockTypeVideoDTO(
    @SerializedName("video") val video: DataDTO?,
    @SerializedName("title") val title: String?
) : BlockTypeBaseDTO()