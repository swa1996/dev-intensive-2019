package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR
fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, unit: TimeUnits): Date {
    this.time += when (unit) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val seconds: Long = ((this.time - date.time) / 1000)
    when {
        seconds > 0 -> when (seconds) {
            in 0..1 -> return "только что"
            in 1..45 -> return "через несколько секунд"
            in 45..75 -> return "через минуту"
            in 75..45 * 60 -> return "через ${TimeUnits.MINUTE.plural((seconds / 60).toInt())}"
            in 45 * 60..75 * 60 -> return "час назад"
            in 75 * 60..22 * 60 * 60 -> return "через ${TimeUnits.HOUR.plural((seconds / (60 * 60)).toInt())}"
            in 22 * 60 * 60..26 * 60 * 60 -> return "через день"
            in 26 * 60 * 60..360 * (60 * 60 * 24) -> return "через ${TimeUnits.DAY.plural((seconds / (60 * 60 * 24)).toInt())}"
            else -> return "более чем через год"
        }
        else -> when {
            seconds >= -1 -> return "только что"
            seconds >= -45 -> return "несколько секунд назад"
            seconds >= -75 -> return "минуту назад"
            seconds >= -45 * 60 -> return "${TimeUnits.MINUTE.plural(Math.abs((seconds / 60).toInt()))} назад"
            seconds >= -75 * 60 -> return "час назад"
            seconds >= -22 * 60 * 60 -> return "${TimeUnits.HOUR.plural(Math.abs((seconds / (60 * 60)).toInt()))} назад"
            seconds >= -26 * 60 * 60 -> return "день назад"
            seconds >= -360 * (60 * 60 * 24) -> return "${TimeUnits.DAY.plural(Math.abs((seconds / (60 * 60 * 24)).toInt()))} назад"
            else -> return "более года назад"
        }
    }
}


enum class TimeUnits {
    SECOND {
        override fun plural(value: Int): String = when (value % 100) {
            in 5..20 -> "$value секунд"
            else -> when (value % 10) {
                1 -> "$value секунда"
                in 2..4 -> "$value секунды"
                else -> "$value секунд"
            }
        }
    },
    MINUTE {
        override fun plural(value: Int) = when (value % 100) {
            in 5..20 -> "$value минут"
            else -> when (value % 10) {
                1 -> "$value минута"
                in 2..4 -> "$value минуты"
                else -> "$value минут"
            }
        }
    },
    HOUR {
        override fun plural(value: Int): String = when (value % 100) {
            in 5..20 -> "$value часов"
            else -> when (value % 10) {
                1 -> "$value час"
                in 2..4 -> "$value часа"
                else -> "$value часов"
            }
        }
    },
    DAY {
        override fun plural(value: Int) = when (value % 100) {
            in 5..20 -> "$value дней"
            else -> when (value % 10) {
                1 -> "$value день"
                in 2..4 -> "$value дня"
                else -> "$value дней"
            }
        }
    };

    abstract fun plural(value: Int): String
}
