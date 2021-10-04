package ru.unit.tjournaltest.data.api

import ru.unit.tjournaltest.data.DataConfig
import javax.inject.Inject

class ApiConfig @Inject constructor(
    config: DataConfig
) {
    val addressApiV1 = config.baseApiHost + "/v1.9/"
    val addressApiV2 = config.baseApiHost + "/v2.0/"
    val subsiteId = config.subsiteId
}