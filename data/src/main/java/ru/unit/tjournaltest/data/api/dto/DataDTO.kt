package ru.unit.tjournaltest.data.api.dto

import com.google.gson.annotations.JsonAdapter
import ru.unit.tjournaltest.data.api.dto.adapter.DataAdapter

@JsonAdapter(DataAdapter::class)
data class DataDTO(
    val type: String?,
    val data: DataTypeBaseDTO?
)
