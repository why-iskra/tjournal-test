package ru.unit.tjournaltest.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.unit.tjournaltest.data.room.dao.TimelineDao
import ru.unit.tjournaltest.data.room.entity.*

@Database(
    entities = [Timeline::class, TimelineCover::class, TimelineTypeVideo::class, TimelineTypeImage::class, TimelineTypeText::class, TimelineExternalService::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun timelineDao(): TimelineDao
}