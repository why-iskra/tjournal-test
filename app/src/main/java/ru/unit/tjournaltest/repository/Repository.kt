package ru.unit.tjournaltest.repository

import ru.unit.tjournaltest.api.TJournal
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repository @Inject constructor(
    val api: TJournal,
    val user: UserRepository,
    val timeline: TimelineRepository
) {
    suspend fun login(login: String, password: String) = api.login(login, password)
}