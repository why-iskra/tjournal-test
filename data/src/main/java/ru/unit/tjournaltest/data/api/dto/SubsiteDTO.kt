package ru.unit.tjournaltest.data.api.dto

import com.google.gson.annotations.SerializedName

data class SubsiteDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("avatar") val avatar: DataDTO,
    @SerializedName("name") val name: String?
)
