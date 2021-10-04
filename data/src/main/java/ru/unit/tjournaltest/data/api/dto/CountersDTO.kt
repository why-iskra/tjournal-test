package ru.unit.tjournaltest.data.api.dto

import com.google.gson.annotations.SerializedName

data class CountersDTO(
    @SerializedName("comments") val comments: Long = 0
)
