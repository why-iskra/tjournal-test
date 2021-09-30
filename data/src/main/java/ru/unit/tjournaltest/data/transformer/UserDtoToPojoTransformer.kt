package ru.unit.tjournaltest.data.transformer

import ru.unit.tjournaltest.data.api.dto.UserResponseDTO
import ru.unit.tjournaltest.domain.user.pojo.UserPOJO
import ru.unit.tjournaltest.domain.user.pojo.UserResultPOJO
import javax.inject.Inject

class UserDtoToPojoTransformer @Inject constructor() : BaseTransformer<UserResponseDTO, UserPOJO> {
    override fun transform(value: UserResponseDTO): UserPOJO {
        var result: UserResultPOJO? = null
        val resultApi = value.result
        if (resultApi != null) {
            result = UserResultPOJO(
                resultApi.name,
                resultApi.karma,
                resultApi.avatarUrl,
                resultApi.mHash,
                resultApi.userHash
            )
        }

        return UserPOJO(value.message, value.success, result)
    }
}