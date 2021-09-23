package ru.unit.tjournaltest.data.api.dto

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import java.lang.reflect.Type


@JsonAdapter(LoginResponseDTODeserializer::class)
data class LoginResponseDTO(
    val message: String,
    val success: Boolean
)

class LoginResponseDTODeserializer : JsonDeserializer<LoginResponseDTO> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LoginResponseDTO {
        if (json == null) return LoginResponseDTO("", false)

        val obj = json.asJsonObject

        return LoginResponseDTO(
            obj.getAsJsonPrimitive("message").asString,
            obj.has("result")
        )
    }
}
