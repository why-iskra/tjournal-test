package ru.unit.tjournaltest.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TimelineExternalService(
    val name: String?,
    val idExternalService: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
