package ru.unit.tjournaltest.data.socket.event

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import java.lang.reflect.Type

@JsonAdapter(MessengerEventDTODeserializer::class)
data class MessengerEventDTO(
    val actionAddMessage: ActionAddMessageDTO?
)

class MessengerEventDTODeserializer : JsonDeserializer<MessengerEventDTO> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): MessengerEventDTO {
        if (json == null) return MessengerEventDTO(null)

        val obj = json.asJsonObject

        return when (obj.getAsJsonPrimitive("action").asString) {
            "addMessage" -> MessengerEventDTO(context?.deserialize(obj.getAsJsonObject("message"), ActionAddMessageDTO::class.java))
            else -> MessengerEventDTO(null)
        }
    }
}