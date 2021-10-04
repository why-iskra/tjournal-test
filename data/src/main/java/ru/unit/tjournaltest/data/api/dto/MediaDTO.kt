package ru.unit.tjournaltest.data.api.dto

import com.google.gson.annotations.SerializedName

data class MediaDTO(
    @SerializedName("image") val imageData: DataDTO?
)