package main.date

import main.date.models.Date
import main.date.models.CustomComparator
import main.date.models.isLeapYear
import main.date.models.isValidDate
import kotlin.random.Random

fun main() {

    println("2020 leap year? ${Date(2020, 1, 1).isLeapYear()}")
    println("2021 leap year? ${Date(2021, 1, 1).isLeapYear()}\n")

    val validDates = mutableListOf<Date>()
    var count = 0

    while (count < 10) {
        val randomDate = randomDate()
        if (randomDate.isValidDate()) {
            validDates.add(randomDate)
            count++
        } else {
            println("Invalid: ${randomDate.year}-${randomDate.month}-${randomDate.day}")
        }
    }

    println("\nValid dates:")
    validDates.forEach { println("${it.year}-${it.month}-${it.day}") }

    val sortedDates = validDates.sorted()
    println("\nSorted dates:")
    sortedDates.forEach { println("${it.year}-${it.month}-${it.day}") }

    val reversedSortedDates = sortedDates.reversed()
    println("\nReversed sorted dates:")
    reversedSortedDates.forEach { println("${it.year}-${it.month}-${it.day}") }

    val sortedDatesWithComparator = validDates.sortedWith(CustomComparator())
    println("\nSorted dates with custom comparator:")
    sortedDatesWithComparator.forEach { println("${it.year}-${it.month}-${it.day}") }

}


fun randomDate(): Date {
    val year = Random.nextInt(1000, 3000)
    val month = Random.nextInt(1, 30)
    val day = Random.nextInt(1, 60)
    return Date(year, month, day)
}