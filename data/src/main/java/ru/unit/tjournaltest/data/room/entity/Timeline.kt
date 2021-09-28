package ru.unit.tjournaltest.data.room.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Timeline(
    @PrimaryKey(autoGenerate = false) val id: Long,

    val subsiteName: String?,
    @Embedded(prefix = "avatar_") val avatar: TimelineTypeImage?,
    val authorName: String?,
    val title: String?,
    val date: Long,
    val comments: Long,
    val rating: Long,

    @Embedded(prefix = "cover_") val cover: TimelineCover?
)