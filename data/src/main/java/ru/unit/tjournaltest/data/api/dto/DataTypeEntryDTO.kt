package ru.unit.tjournaltest.data.api.dto

import com.google.gson.annotations.SerializedName

data class DataTypeEntryDTO(
    @SerializedName("id") val id: Long = 0,
    @SerializedName("title") val title: String?,
    @SerializedName("date") val date: Long = 0,
    @SerializedName("author") val author: SubsiteDTO?,
    @SerializedName("subsite") val subsite: SubsiteDTO?,
    @SerializedName("blocks") val blocks: List<BlockDTO> = emptyList(),
    @SerializedName("counters") val counters: CountersDTO,
    @SerializedName("likes") val likes: LikesDTO
) : DataTypeBaseDTO()
