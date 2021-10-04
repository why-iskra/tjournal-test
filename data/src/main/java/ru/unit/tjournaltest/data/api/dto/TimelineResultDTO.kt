package ru.unit.tjournaltest.data.api.dto

import com.google.gson.annotations.SerializedName

data class TimelineResultDTO(
    @SerializedName("items") val items: List<DataDTO> = emptyList(),
    @SerializedName("lastId") val lastId: String?,
    @SerializedName("lastSortingValue") val lastSortingValue: String?
)
