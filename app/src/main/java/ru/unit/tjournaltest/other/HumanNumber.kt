package ru.unit.tjournaltest.other

import java.text.DecimalFormat


fun humanNumber(number: Long): String {
    if(number <= 0L) return "0"

    val dividers = arrayOf(1000000000L, 1000000L, 1000L, 1L)
    val units = arrayOf("B", "M", "K", "")

    var result = "wrong value"
    for (i in dividers.indices) {
        val divider = dividers[i]
        if (number >= divider) {
            result = format(number, divider, units[i])
            break
        }
    }
    return result
}

private fun format(
    value: Long,
    divider: Long,
    unit: String
): String {
    val result = if (divider > 1) value.toDouble() / divider.toDouble() else value.toDouble()
    return DecimalFormat("#.##").format(result).toString() + unit
}
