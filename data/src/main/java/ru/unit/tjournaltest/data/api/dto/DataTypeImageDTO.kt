package ru.unit.tjournaltest.data.api.dto

import com.google.gson.annotations.SerializedName

data class DataTypeImageDTO(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("type") val type: String,
    @SerializedName("external_service") val externalService: ExternalServiceDTO?
) : DataTypeBaseDTO()
