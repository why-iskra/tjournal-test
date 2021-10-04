package ru.unit.tjournaltest.data.api.dto

import com.google.gson.annotations.SerializedName

data class LikesDTO(
    @SerializedName("counter") val counter: Long = 0
)
