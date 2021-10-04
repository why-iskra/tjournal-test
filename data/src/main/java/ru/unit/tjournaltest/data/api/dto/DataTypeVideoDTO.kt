package ru.unit.tjournaltest.data.api.dto

import com.google.gson.annotations.SerializedName

data class DataTypeVideoDTO(
    @SerializedName("thumbnail") val dataImage: DataDTO?,
    @SerializedName("external_service") val externalService: ExternalServiceDTO?
) : DataTypeBaseDTO()
