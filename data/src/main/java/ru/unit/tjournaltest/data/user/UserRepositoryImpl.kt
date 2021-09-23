package ru.unit.tjournaltest.data.user

import ru.unit.tjournaltest.data.other.RamCache
import ru.unit.tjournaltest.domain.user.UserRepository
import ru.unit.tjournaltest.domain.user.entity.UserEntity
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {

    private val cacheUserMe = RamCache<UserEntity>()

    override suspend fun getRamCacheUserMe(): UserEntity? = cacheUserMe.get()

    override suspend fun putRamCacheUserMe(value: UserEntity) {
        cacheUserMe.put(value)
    }

    override suspend fun clearRamCacheUserMe() {
        cacheUserMe.clear()
    }

}