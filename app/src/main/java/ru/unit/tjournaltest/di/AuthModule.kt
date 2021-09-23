package ru.unit.tjournaltest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unit.tjournaltest.data.auth.AuthServiceImpl
import ru.unit.tjournaltest.domain.auth.AuthService
import ru.unit.tjournaltest.domain.auth.AuthUseCase
import ru.unit.tjournaltest.domain.auth.AuthUseCaseImpl

@Module(includes = [AuthModule.BindsModule::class])
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class BindsModule {
        @Binds
        abstract fun bindAuthService(impl: AuthServiceImpl): AuthService

        @Binds
        abstract fun bindAuthUseCase(impl: AuthUseCaseImpl): AuthUseCase
    }

}
