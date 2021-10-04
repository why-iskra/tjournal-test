package ru.unit.tjournaltest.data.api.dto

import com.google.gson.annotations.JsonAdapter
import ru.unit.tjournaltest.data.api.dto.adapter.BlockAdapter

@JsonAdapter(BlockAdapter::class)
data class BlockDTO(
    val type: String?,
    val data: BlockTypeBaseDTO?
)
