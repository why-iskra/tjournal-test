package ru.unit.tjournaltest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.unit.tjournaltest.data.user.UserRepositoryImpl
import ru.unit.tjournaltest.data.user.UserServiceImpl
import ru.unit.tjournaltest.domain.user.UserRepository
import ru.unit.tjournaltest.domain.user.UserService
import ru.unit.tjournaltest.domain.user.UserUseCase
import ru.unit.tjournaltest.domain.user.UserUseCaseImpl

@Module(includes = [UserModule.BindsModule::class])
@InstallIn(SingletonComponent::class)
object UserModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class BindsModule {
        @Binds
        abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

        @Binds
        abstract fun bindUserService(impl: UserServiceImpl): UserService

        @Binds
        abstract fun bindUserUseCase(impl: UserUseCaseImpl): UserUseCase
    }

}
