package main.date.models

import java.time.LocalDate

data class Date(val year: Int = LocalDate.now().year,
                val month: Int = LocalDate.now().monthValue,
                val day: Int = LocalDate.now().dayOfMonth) : Comparable<Date> {
    override fun compareTo(other: Date): Int {
        if (year != other.year) {
            return year - other.year
        }
        if (month != other.month) {
            return month - other.month
        }
        return day - other.day
    }

}

fun Date.isLeapYear(): Boolean {
    return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
}

fun Date.isValidDate(): Boolean {
    try {
        LocalDate.of(year, month, day)
        return true
    } catch (e: Exception) {
        return false
    }
}