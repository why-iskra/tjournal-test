package ru.unit.tjournaltest.data.api.dto

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type

@JsonAdapter(UserResponseDTODeserializer::class)
data class UserResponseDTO(
    val message: String,
    val success: Boolean,
    val result: UserInfoDTO?
)

data class UserInfoDTO(
    @SerializedName("name") val name: String,
    @SerializedName("karma") val karma: Int,
    @SerializedName("avatar_url") val avatarUrl: String?,
    @SerializedName("m_hash") val mHash: String?,
    @SerializedName("user_hash") val userHash: String?
)

class UserResponseDTODeserializer : JsonDeserializer<UserResponseDTO> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): UserResponseDTO {
        if (json == null) return UserResponseDTO("", false, null)

        val obj = json.asJsonObject

        return UserResponseDTO(
            obj.getAsJsonPrimitive("message").asString,
            obj.has("result"),
            context?.deserialize<UserInfoDTO>(obj.getAsJsonObject("result"), UserInfoDTO::class.java)
        )
    }
}
