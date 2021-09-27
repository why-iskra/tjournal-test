package ru.unit.tjournaltest.data.room

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DatabaseVar @Inject constructor(
    @ApplicationContext context: Context
) {
    var database = Room.databaseBuilder(context, AppDatabase::class.java, "database")
        .fallbackToDestructiveMigration()
        .build()
}