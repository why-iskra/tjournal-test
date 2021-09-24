package ru.unit.tjournaltest.data.user

import ru.unit.tjournaltest.data.api.TJournalServiceV1
import ru.unit.tjournaltest.data.converter.UserConverter
import ru.unit.tjournaltest.domain.user.UserService
import javax.inject.Inject

class UserServiceImpl @Inject constructor(
    private val apiV1: TJournalServiceV1
) : UserService {
    override suspend fun login(login: String, password: String) = UserConverter.apiResponseToPOJO(apiV1.login(login, password))

    override suspend fun getUserMe() = UserConverter.apiResponseToPOJO(apiV1.userMe())

}