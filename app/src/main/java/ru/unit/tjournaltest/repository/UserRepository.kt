package ru.unit.tjournaltest.repository

import ru.unit.barsdiary.other.CacheFunction

object UserRepository {

    private val cachedUserMe = CacheFunction {
        RepositoryApiController.apiV1.userMeRequest()
    }

    suspend fun getUserMe() = cachedUserMe()

    suspend fun reset() {
        cachedUserMe.reset()
    }
}