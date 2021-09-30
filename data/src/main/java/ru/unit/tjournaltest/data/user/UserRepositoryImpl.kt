package ru.unit.tjournaltest.data.user

import ru.unit.tjournaltest.data.sharedpreferences.SharedPreferencesUser
import ru.unit.tjournaltest.domain.user.UserRepository
import ru.unit.tjournaltest.domain.user.pojo.UserPOJO
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userPreferences: SharedPreferencesUser
) : UserRepository {
    override suspend fun getUserMe(): UserPOJO? = userPreferences.userMe

    override suspend fun putUserMe(value: UserPOJO) {
        userPreferences.userMe = value
    }

    override suspend fun clearUserMe() {
        userPreferences.clear()
    }

}