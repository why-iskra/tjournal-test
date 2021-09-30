package ru.unit.tjournaltest.data.json.annotation

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GsonBeautiful()

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GsonSimple()
