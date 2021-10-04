package ru.unit.tjournaltest.data.api.dto.adapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import ru.unit.tjournaltest.data.api.dto.ExternalServiceDTO
import java.lang.reflect.Type

class ExternalServiceAdapter : JsonDeserializer<ExternalServiceDTO> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): ExternalServiceDTO {
        return if (json == null || json.isJsonArray) {
            ExternalServiceDTO(null, null)
        } else {
            val obj = json.asJsonObject
            ExternalServiceDTO(
                obj.getAsJsonPrimitive("name").asString,
                obj.getAsJsonPrimitive("id").asString
            )
        }
    }

}