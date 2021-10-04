package ru.unit.tjournaltest.data.api.dto

import com.google.gson.annotations.SerializedName

data class BlockTypeMediaDTO(
    @SerializedName("items") val items: List<MediaDTO> = emptyList()
) : BlockTypeBaseDTO()
