package ru.unit.tjournaltest.data.socket.annotation

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SocketEventFlow

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class SocketStateFlow
