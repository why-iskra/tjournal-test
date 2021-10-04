package ru.unit.tjournaltest.data.json.annotation

import javax.inject.Qualifier

// For debugging

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GsonBeautiful

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GsonSimple
