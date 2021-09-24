package ru.unit.tjournaltest.domain.user

import ru.unit.tjournaltest.domain.user.pojo.UserPOJO

interface UserRepository {
    suspend fun getUserMe(): UserPOJO
    suspend fun putUserMe(value: UserPOJO)
    suspend fun clearUserMe()
}