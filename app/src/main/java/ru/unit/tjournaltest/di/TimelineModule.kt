package ru.unit.tjournaltest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unit.tjournaltest.data.timeline.TimelineRepositoryImpl
import ru.unit.tjournaltest.domain.timeline.TimelineRepository

@Module(includes = [TimelineModule.BindsModule::class])
@InstallIn(SingletonComponent::class)
object TimelineModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class BindsModule {
        @Binds
        abstract fun bindTimelineRepository(impl: TimelineRepositoryImpl): TimelineRepository
    }

}
