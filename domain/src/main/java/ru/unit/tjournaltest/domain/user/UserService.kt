package ru.unit.tjournaltest.domain.user

import ru.unit.tjournaltest.domain.user.entity.UserEntity

interface UserService {
    suspend fun getUserMe(): UserEntity
}