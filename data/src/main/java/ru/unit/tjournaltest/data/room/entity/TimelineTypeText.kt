package ru.unit.tjournaltest.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TimelineTypeText(
    val text: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
