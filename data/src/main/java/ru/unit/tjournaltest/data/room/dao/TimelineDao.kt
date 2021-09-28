package ru.unit.tjournaltest.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.unit.tjournaltest.data.room.entity.Timeline


@Dao
interface TimelineDao {
    @Query("SELECT * FROM timeline ORDER BY date DESC LIMIT 10 OFFSET :page * 10")
    fun getPage(page: Int): List<Timeline>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(timeline: List<Timeline>)

    @Query("DELETE FROM timeline")
    fun deleteAll()
}