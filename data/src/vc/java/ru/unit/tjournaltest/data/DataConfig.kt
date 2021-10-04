package ru.unit.tjournaltest.data

import javax.inject.Inject

class DataConfig @Inject constructor() {
    val baseApiHost = "https://api.vc.ru"
    val baseSocketHost = "https://ws-sio.vc.ru"
    val subsiteId = 199114
    val subsiteName = "Дизайн"
}