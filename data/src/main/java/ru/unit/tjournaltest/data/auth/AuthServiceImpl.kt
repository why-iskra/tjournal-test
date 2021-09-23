package ru.unit.tjournaltest.data.auth

import ru.unit.tjournaltest.data.api.TJournalServiceV1
import ru.unit.tjournaltest.data.converter.AuthConverter
import ru.unit.tjournaltest.domain.auth.AuthService
import javax.inject.Inject

class AuthServiceImpl @Inject constructor(
    private val apiV1: TJournalServiceV1
) : AuthService {
    override suspend fun login(login: String, password: String) = AuthConverter.apiResponseToEntity(apiV1.login(login, password))

}