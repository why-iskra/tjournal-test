package ru.unit.tjournaltest.data.user

import ru.unit.tjournaltest.data.api.TJournalServiceV1
import ru.unit.tjournaltest.data.transformer.UserDtoToPojoTransformer
import ru.unit.tjournaltest.domain.user.UserService
import javax.inject.Inject

class UserServiceImpl @Inject constructor(
    private val apiV1: TJournalServiceV1,
    private val userDtoToPojoTransformer: UserDtoToPojoTransformer
) : UserService {
    override suspend fun login(login: String, password: String) = userDtoToPojoTransformer.transform(apiV1.login(login, password))

    override suspend fun getUserMe() = userDtoToPojoTransformer.transform(apiV1.userMe())

}