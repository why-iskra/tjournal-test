package ru.unit.tjournaltest.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.unit.tjournaltest.data.api.dto.TimelineResponseDTO

interface TJournalServiceV2 {
    @GET("timeline")
    suspend fun videoAndGifs(
        @Query("subsitesIds") subsitesIds: String,
        @Query("sorting") sorting: String,
        @Query("allSite") allSite: Boolean,
        @Query("lastId") lastId: String,
        @Query("lastSortingValue") lastSortingValue: String
    ): TimelineResponseDTO
}