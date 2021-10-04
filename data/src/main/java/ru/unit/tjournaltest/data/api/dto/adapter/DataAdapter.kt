package ru.unit.tjournaltest.data.api.dto.adapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import ru.unit.tjournaltest.data.api.dto.DataDTO
import ru.unit.tjournaltest.data.api.dto.DataTypeEntryDTO
import ru.unit.tjournaltest.data.api.dto.DataTypeImageDTO
import ru.unit.tjournaltest.data.api.dto.DataTypeVideoDTO
import java.lang.reflect.Type

class DataAdapter : JsonDeserializer<DataDTO> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): DataDTO {
        json ?: return DataDTO(null, null)

        val obj = json.asJsonObject
        val jsonData = obj.getAsJsonObject("data")
        val type = obj.getAsJsonPrimitive("type").asString
        val data = when (type) {
            "image" -> context?.deserialize<DataTypeImageDTO>(jsonData, DataTypeImageDTO::class.java)
            "entry" -> context?.deserialize<DataTypeEntryDTO>(jsonData, DataTypeEntryDTO::class.java)
            "video" -> context?.deserialize<DataTypeVideoDTO>(jsonData, DataTypeVideoDTO::class.java)
            else -> null
        }

        return DataDTO(type, data)
    }
}