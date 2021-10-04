package ru.unit.tjournaltest.data.api.dto.adapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import ru.unit.tjournaltest.data.api.dto.BlockDTO
import ru.unit.tjournaltest.data.api.dto.BlockTypeMediaDTO
import ru.unit.tjournaltest.data.api.dto.BlockTypeTextDTO
import ru.unit.tjournaltest.data.api.dto.BlockTypeVideoDTO
import java.lang.reflect.Type

class BlockAdapter : JsonDeserializer<BlockDTO> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): BlockDTO {
        json ?: return BlockDTO(null, null)

        val obj = json.asJsonObject
        val jsonData = obj.getAsJsonObject("data")
        val type = obj.getAsJsonPrimitive("type").asString
        val data = when (type) {
            "media" -> context?.deserialize<BlockTypeMediaDTO>(jsonData, BlockTypeMediaDTO::class.java)
            "video" -> context?.deserialize<BlockTypeVideoDTO>(jsonData, BlockTypeVideoDTO::class.java)
            "text" -> context?.deserialize<BlockTypeTextDTO>(jsonData, BlockTypeTextDTO::class.java)
            else -> null
        }

        return BlockDTO(type, data)
    }
}