package ru.unit.tjournaltest.other

import ru.unit.tjournaltest.api.v2.TJournalV2

object DifferentUtils {
    fun apiGenImageRectUrl(uuid: String, s: Int) = TJournalV2.genImageRectUrl(uuid, s)
    fun apiGenImageUrl(uuid: String) = TJournalV2.genImageUrl(uuid)
    fun apiGenImageGifMP4Url(uuid: String) = TJournalV2.genImageGifMP4Url(uuid)
}