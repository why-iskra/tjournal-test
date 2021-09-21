package ru.unit.tjournaltest.repository

import ru.unit.tjournaltest.api.v1.TJournalV1
import ru.unit.tjournaltest.api.v2.TJournalV2

class RepositoryApiController {
    companion object {
        lateinit var instance: RepositoryApiController // FIXME: bad code
        fun initialize() {
            instance = RepositoryApiController()
        }
    }

    val apiV1 = TJournalV1()
    val apiV2 = TJournalV2()

    suspend fun login(login: String, password: String) = apiV1.login(login, password)
}