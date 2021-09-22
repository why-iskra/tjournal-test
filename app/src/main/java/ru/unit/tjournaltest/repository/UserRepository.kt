package ru.unit.tjournaltest.repository

import ru.unit.barsdiary.other.CacheFunction
import ru.unit.tjournaltest.api.TJournal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val api: TJournal
) {

    private val cachedUserMe = CacheFunction {
        api.userMeRequest()
    }

    suspend fun getUserMe() = cachedUserMe()

    suspend fun reset() {
        cachedUserMe.reset()
    }
}