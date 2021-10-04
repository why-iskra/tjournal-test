package ru.unit.tjournaltest.data.api.dto

import com.google.gson.annotations.SerializedName

data class TimelineResponseDTO(
    @SerializedName("message") val message: String?,
    @SerializedName("result") val result: TimelineResultDTO?
)