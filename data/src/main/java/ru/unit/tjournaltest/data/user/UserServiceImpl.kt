package ru.unit.tjournaltest.data.user

import ru.unit.tjournaltest.data.api.TJournalServiceV1
import ru.unit.tjournaltest.data.converter.UserConverter
import ru.unit.tjournaltest.domain.user.UserService
import javax.inject.Inject

class UserServiceImpl @Inject constructor(
    private val apiV1: TJournalServiceV1
) : UserService {
    override suspend fun getUserMe()
        = UserConverter.apiResponseToEntity(apiV1.userMe())

}