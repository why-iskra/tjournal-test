package ru.unit.tjournaltest.data

import javax.inject.Inject

class DataConfig @Inject constructor() {
    val baseApiHost = "https://api.tjournal.ru"
    val baseSocketHost = "https://ws-sio.tjournal.ru"
    val subsiteId = 237832
    val subsiteName = "Видео и гифки"
}