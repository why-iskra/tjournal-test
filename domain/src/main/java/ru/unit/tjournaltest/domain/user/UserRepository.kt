package ru.unit.tjournaltest.domain.user

import ru.unit.tjournaltest.domain.user.pojo.UserPOJO

interface UserRepository {
    suspend fun getUserMe(): UserPOJO?
    suspend fun setUserMe(value: UserPOJO)
    fun getXDeviceToken(): String?
    fun setXDeviceToken(value: String)
    suspend fun clearUserMe()
}