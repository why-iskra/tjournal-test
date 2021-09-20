package ru.unit.tjournaltest.repository

import ru.unit.tjournaltest.api.v1.TJournalV1
import ru.unit.tjournaltest.api.v2.TJournalV2

object RepositoryApiController {
    val apiV1 = TJournalV1()
    val apiV2 = TJournalV2()
}