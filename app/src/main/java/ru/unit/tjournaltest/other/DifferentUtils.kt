package ru.unit.tjournaltest.other

object DifferentUtils {
    fun apiGenImageRectUrl(uuid: String, s: Int) = "https://leonardo.osnova.io/%s/-/scale_crop/%dx%d/".format(uuid, s, s)
    fun apiGenImageUrl(uuid: String) = "https://leonardo.osnova.io/%s/".format(uuid)
    fun apiGenImageGifMP4Url(uuid: String) = "https://leonardo.osnova.io/%s/-/format/mp4/".format(uuid)
}