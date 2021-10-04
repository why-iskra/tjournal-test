package ru.unit.tjournaltest.data.api.dto

import com.google.gson.annotations.SerializedName

data class UserResultDTO(
    @SerializedName("name") val name: String,
    @SerializedName("karma") val karma: Int,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("m_hash") val mHash: String,
    @SerializedName("user_hash") val userHash: String
)
