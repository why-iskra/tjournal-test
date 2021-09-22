package ru.unit.tjournaltest.di.annotation

import javax.inject.Qualifier

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApiOkHttpClient

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApiRetrofitBuilder
