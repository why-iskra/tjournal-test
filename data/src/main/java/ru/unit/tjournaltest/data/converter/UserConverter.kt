package ru.unit.tjournaltest.data.converter

import ru.unit.tjournaltest.data.api.dto.UserResponseDTO
import ru.unit.tjournaltest.domain.user.entity.UserAvatarEntity
import ru.unit.tjournaltest.domain.user.entity.UserEntity
import ru.unit.tjournaltest.domain.user.entity.UserResultEntity

object UserConverter {
    fun apiResponseToEntity(value: UserResponseDTO): UserEntity {
        var result: UserResultEntity? = null
        val resultApi = value.result
        if(resultApi != null) {
            result = UserResultEntity(
                resultApi.name,
                resultApi.karma,
                UserAvatarEntity(resultApi.avatar.data.type, resultApi.avatar.data.uuid)
            )
        }

        return UserEntity(value.message, value.success, result)
    }
}