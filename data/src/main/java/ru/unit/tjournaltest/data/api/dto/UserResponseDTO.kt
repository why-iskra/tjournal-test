package ru.unit.tjournaltest.data.api.dto

import com.google.gson.annotations.SerializedName

data class UserResponseDTO(
    @SerializedName("message") val message: String?,
    @SerializedName("result") val result: UserResultDTO?
)