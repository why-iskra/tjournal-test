package ru.unit.tjournaltest.data.socket.event

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import java.lang.reflect.Type

@JsonAdapter(EventDTODeserializer::class)
data class EventDTO(
    val messengerEvent: MessengerEventDTO?
)

class EventDTODeserializer : JsonDeserializer<EventDTO> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): EventDTO {
        if (json == null) return EventDTO(null)

        val obj = json.asJsonObject
        val data = obj.get("data").asJsonObject

        return when (data.getAsJsonPrimitive("type").asString) {
            "messenger_event" -> EventDTO(context?.deserialize(data, MessengerEventDTO::class.java))
            else -> EventDTO(null)
        }
    }
}
