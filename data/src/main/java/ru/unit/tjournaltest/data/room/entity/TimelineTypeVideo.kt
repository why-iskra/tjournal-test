package ru.unit.tjournaltest.data.room.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TimelineTypeVideo(
    @Embedded(prefix = "thumbnail_") val thumbnail: TimelineTypeImage?,
    val title: String?,
    @Embedded(prefix = "external_service_") val externalService: TimelineExternalService?
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
