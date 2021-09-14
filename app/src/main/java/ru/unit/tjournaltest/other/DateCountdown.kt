package ru.unit.tjournaltest.other

import android.content.Context
import ru.unit.tjournaltest.R
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit

fun dateCountdown(context: Context, start: LocalDateTime, current: LocalDateTime): String {
    val differenceSecond: Long = ChronoUnit.SECONDS.between(start, current)
    val differenceMinute: Long = ChronoUnit.MINUTES.between(start, current)
    val differenceHour: Long = ChronoUnit.HOURS.between(start, current)
    val differenceDay: Long = ChronoUnit.DAYS.between(start, current)
    val differenceMonth: Long = ChronoUnit.MONTHS.between(start, current)
    val differenceYear: Long = ChronoUnit.YEARS.between(start, current)

    when {
        differenceYear > 0 -> {
            return "%d %s".format(differenceYear, when(differenceYear) {
                1L -> context.resources.getString(R.string.year)
                2L, 3L, 4L -> context.resources.getString(R.string.years)
                else -> context.resources.getString(R.string.yearsAfterFour)
            })
        }
        differenceMonth > 30 -> {
            return "%d %s".format(differenceMonth, when(differenceMonth) {
                1L -> context.resources.getString(R.string.month)
                2L, 3L, 4L -> context.resources.getString(R.string.months)
                else -> context.resources.getString(R.string.monthsAfterFour)
            })
        }
        differenceDay > 0 -> {
            return "%d %s".format(differenceDay, when(differenceDay) {
                1L -> context.resources.getString(R.string.day)
                2L, 3L, 4L -> context.resources.getString(R.string.days)
                else -> context.resources.getString(R.string.daysAfterFour)
            })
        }
        differenceHour > 0 -> {
            return "%d %s".format(differenceHour, when(differenceHour) {
                1L -> context.resources.getString(R.string.hour)
                2L, 3L, 4L -> context.resources.getString(R.string.hours)
                else -> context.resources.getString(R.string.hoursAfterFour)
            })
        }
        differenceMinute > 0 -> {
            return "%d %s".format(differenceMinute, when(differenceMinute) {
                1L -> context.resources.getString(R.string.minute)
                2L, 3L, 4L -> context.resources.getString(R.string.minutes)
                else -> context.resources.getString(R.string.minutesAfterFour)
            })
        }
        else -> {
            return "%d %s".format(differenceSecond, when(differenceSecond) {
                1L -> context.resources.getString(R.string.second)
                2L, 3L, 4L -> context.resources.getString(R.string.seconds)
                else -> context.resources.getString(R.string.secondsAfterFour)
            })
        }
    }
}
