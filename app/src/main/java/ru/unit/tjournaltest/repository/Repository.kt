package ru.unit.tjournaltest.repository

import ru.unit.tjournaltest.api.TJournal
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class Repository @Inject constructor() {
    @Inject
    lateinit var api: TJournal

    @Inject
    lateinit var user: UserRepository

    @Inject
    lateinit var timeline: TimelineRepository

    suspend fun login(login: String, password: String) = api.login(login, password)
}