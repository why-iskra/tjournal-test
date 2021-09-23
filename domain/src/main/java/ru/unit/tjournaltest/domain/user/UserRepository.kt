package ru.unit.tjournaltest.domain.user

import ru.unit.tjournaltest.domain.user.entity.UserEntity

interface UserRepository {
    suspend fun getRamCacheUserMe(): UserEntity?
    suspend fun putRamCacheUserMe(value: UserEntity)
    suspend fun clearRamCacheUserMe()
}