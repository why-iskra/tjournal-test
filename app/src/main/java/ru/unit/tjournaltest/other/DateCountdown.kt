package ru.unit.tjournaltest.other

import android.content.Context
import ru.unit.tjournaltest.R
import java.util.*

fun dateCountdown(context: Context, startSeconds: Long): String {
    val current = Calendar.getInstance()
    val start = Calendar.getInstance().apply { timeInMillis = startSeconds * 1000 }
    
    val differenceSecond = current.get(Calendar.SECOND) - start.get(Calendar.SECOND)
    val differenceMinute = current.get(Calendar.MINUTE) - start.get(Calendar.MINUTE)
    val differenceHour = current.get(Calendar.HOUR) - start.get(Calendar.HOUR)
    val differenceDay = current.get(Calendar.DAY_OF_YEAR) - start.get(Calendar.DAY_OF_YEAR)
    val differenceMonth = current.get(Calendar.MONTH) - start.get(Calendar.MONTH)
    val differenceYear = current.get(Calendar.YEAR) - start.get(Calendar.YEAR)

    return when {
        differenceYear > 0 -> "%d %s".format(differenceYear, context.resources.getQuantityString(R.plurals.plural_year, differenceYear))
        differenceMonth > 0 -> "%d %s".format(differenceMonth, context.resources.getQuantityString(R.plurals.plural_month, differenceMonth))
        differenceDay > 0 -> "%d %s".format(differenceDay, context.resources.getQuantityString(R.plurals.plural_day, differenceDay))
        differenceHour > 0 -> "%d %s".format(differenceHour, context.resources.getQuantityString(R.plurals.plural_hour, differenceHour))
        differenceMinute > 0 -> "%d %s".format(differenceMinute, context.resources.getQuantityString(R.plurals.plural_minute, differenceMinute))
        else -> "%d %s".format(differenceSecond, context.resources.getQuantityString(R.plurals.plural_second, differenceSecond))
    }
}
