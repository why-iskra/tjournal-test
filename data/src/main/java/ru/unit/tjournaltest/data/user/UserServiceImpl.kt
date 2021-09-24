package ru.unit.tjournaltest.data.user

import ru.unit.tjournaltest.data.api.TJournalServiceV1
import ru.unit.tjournaltest.data.transformer.UserDtoTransformer
import ru.unit.tjournaltest.domain.user.UserService
import javax.inject.Inject

class UserServiceImpl @Inject constructor(
    private val apiV1: TJournalServiceV1,
    private val userDtoTransformer: UserDtoTransformer
) : UserService {
    override suspend fun login(login: String, password: String) = userDtoTransformer.transform(apiV1.login(login, password))

    override suspend fun getUserMe() = userDtoTransformer.transform(apiV1.userMe())

}