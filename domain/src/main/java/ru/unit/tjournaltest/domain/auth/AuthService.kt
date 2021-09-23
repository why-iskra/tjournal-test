package ru.unit.tjournaltest.domain.auth

import ru.unit.tjournaltest.domain.auth.entity.AuthEntity

interface AuthService {
    suspend fun login(login: String, password: String): AuthEntity
}