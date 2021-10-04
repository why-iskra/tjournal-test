package ru.unit.tjournaltest.data.user

import ru.unit.tjournaltest.data.sharedpreferences.SharedPreferencesAuth
import ru.unit.tjournaltest.data.sharedpreferences.SharedPreferencesUser
import ru.unit.tjournaltest.domain.user.UserRepository
import ru.unit.tjournaltest.domain.user.pojo.UserPOJO
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userPreferences: SharedPreferencesUser,
    private val authPreferences: SharedPreferencesAuth
) : UserRepository {
    override suspend fun getUserMe(): UserPOJO? = userPreferences.userMe

    override suspend fun setUserMe(value: UserPOJO) {
        userPreferences.userMe = value
    }

    override fun getXDeviceToken(): String? = authPreferences.xDeviceToken

    override fun setXDeviceToken(value: String) {
        authPreferences.xDeviceToken = value
    }

    override suspend fun clearUserMe() {
        userPreferences.clear()
    }

}