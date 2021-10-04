package ru.unit.tjournaltest.data.socket

enum class SocketState {
    NOTHING,
    CONNECTED,
    DISCONNECTED,
    EVENT,
    EVENT_ERROR,
    CONNECTION_ERROR
}