package ru.unit.tjournaltest.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TimelineTypeImage(
    val typeImage: String?,
    val uuid: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
