package ru.unit.tjournaltest.data.room.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TimelineCover(
    val type: String?,

    @Embedded(prefix = "text_") val text: TimelineTypeText?,
    @Embedded(prefix = "video_") val video: TimelineTypeVideo?,
    @Embedded(prefix = "image_") val image: TimelineTypeImage?
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
