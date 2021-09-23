package ru.unit.tjournaltest.domain.auth

import ru.unit.tjournaltest.domain.auth.entity.AuthEntity
import javax.inject.Inject

interface AuthUseCase {
    suspend fun login(login: String, password: String): AuthEntity
}

class AuthUseCaseImpl @Inject constructor(
    private val authService: AuthService
) : AuthUseCase {
    override suspend fun login(login: String, password: String) = authService.login(login, password)

}