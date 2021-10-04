package ru.unit.tjournaltest.data.json

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unit.tjournaltest.data.json.annotation.GsonBeautiful
import ru.unit.tjournaltest.data.json.annotation.GsonSimple

@Module
@InstallIn(SingletonComponent::class)
object GsonModule {

    @Provides
    @GsonBeautiful
    fun provideGsonBeautiful(): Gson = GsonBuilder().setPrettyPrinting().create()

    @Provides
    @GsonSimple
    fun provideGsonSimple(): Gson = Gson()
}