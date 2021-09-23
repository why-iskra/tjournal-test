package ru.unit.tjournaltest.domain.user

import ru.unit.tjournaltest.domain.user.entity.UserEntity
import javax.inject.Inject

interface UserUseCase {
    suspend fun getUserMe(): UserEntity
    suspend fun clearCache()
}

class UserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val userService: UserService
) : UserUseCase {
    override suspend fun getUserMe(): UserEntity {
        val cacheResult = userRepository.getRamCacheUserMe()
        if (cacheResult != null) {
            return cacheResult
        }

        val apiResult = userService.getUserMe()
        userRepository.putRamCacheUserMe(apiResult)

        return apiResult
    }

    override suspend fun clearCache() {
        userRepository.clearRamCacheUserMe()
    }

}
