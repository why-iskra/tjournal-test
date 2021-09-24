package ru.unit.tjournaltest.data.user

import ru.unit.tjournaltest.data.sharedpreferences.SharedPreferencesUser
import ru.unit.tjournaltest.domain.user.UserRepository
import ru.unit.tjournaltest.domain.user.pojo.UserPOJO
import ru.unit.tjournaltest.domain.user.pojo.UserResultPOJO
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userPreferences: SharedPreferencesUser
) : UserRepository {
    override suspend fun getUserMe(): UserPOJO? {
        val avatarUrl = userPreferences.avatarUrl
        val name = userPreferences.name
        val karma = userPreferences.karma

        return if (name.isNullOrEmpty() || avatarUrl.isNullOrEmpty())
            null
        else
            UserPOJO(
                "",
                true,
                UserResultPOJO(
                    name,
                    karma,
                    avatarUrl
                )
            )
    }

    override suspend fun putUserMe(value: UserPOJO) {
        userPreferences.name = value.result?.name
        userPreferences.karma = value.result?.karma ?: 0
        userPreferences.avatarUrl = value.result?.avatarUrl
    }

    override suspend fun clearUserMe() {
        userPreferences.clear()
    }

}