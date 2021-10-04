package ru.unit.tjournaltest.data.api.dto

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import ru.unit.tjournaltest.data.api.dto.adapter.ExternalServiceAdapter

@JsonAdapter(ExternalServiceAdapter::class)
data class ExternalServiceDTO(
    @SerializedName("name") val name: String?,
    @SerializedName("id") val id: String?
)
