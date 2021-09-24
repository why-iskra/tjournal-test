package ru.unit.tjournaltest.data.converter

import ru.unit.tjournaltest.data.api.dto.UserResponseDTO
import ru.unit.tjournaltest.domain.user.pojo.UserPOJO
import ru.unit.tjournaltest.domain.user.pojo.UserResultPOJO

object UserConverter {
    fun apiResponseToPOJO(value: UserResponseDTO): UserPOJO {
        var result: UserResultPOJO? = null
        val resultApi = value.result
        if (resultApi != null) {
            result = UserResultPOJO(
                resultApi.name,
                resultApi.karma,
                resultApi.avatarUrl
            )
        }

        return UserPOJO(value.message, value.success, result)
    }
}