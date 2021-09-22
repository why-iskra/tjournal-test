package ru.unit.tjournaltest.api

import ru.unit.tjournaltest.api.dto.LoginResponseDTO
import ru.unit.tjournaltest.api.dto.TimelineResponseDTO
import ru.unit.tjournaltest.api.dto.UserResponseDTO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TJournal @Inject constructor(
    private val serviceV1: TJournalServiceV1,
    private val serviceV2: TJournalServiceV2,
) {

    companion object {
        const val addressApiV1 = "https://api.tjournal.ru/v1.8/"
        const val addressApiV2 = "https://api.tjournal.ru/v2.0/"
        private const val videoAndGifsId = "237832"
    }

    suspend fun login(login: String, password: String): LoginResponseDTO = serviceV1.login(login, password)

    suspend fun userMeRequest(): UserResponseDTO = serviceV1.userMe()

    suspend fun videoAndGifsRequest(): TimelineResponseDTO =
        serviceV2.videoAndGifs(videoAndGifsId, "hotness", false)

    suspend fun videoAndGifsRequest(lastId: String, lastSortingValue: String): TimelineResponseDTO =
        serviceV2.videoAndGifs(videoAndGifsId, "hotness", false, lastId, lastSortingValue)

}