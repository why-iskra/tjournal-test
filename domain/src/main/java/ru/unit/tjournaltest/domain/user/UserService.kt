package ru.unit.tjournaltest.domain.user

import ru.unit.tjournaltest.domain.user.pojo.UserPOJO

interface UserService {
    suspend fun login(login: String, password: String): UserPOJO

    suspend fun getUserMe(): UserPOJO

}