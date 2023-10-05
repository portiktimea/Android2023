package main

import java.util.*

fun main(args: Array<String>) {

    //1
    val a = 5
    val b = 6
    println("\n$a + $b = ${a + b}\n")



    //2
    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    for(day in daysOfWeek){
        println(day)
    }

    println("\nDays starting with T:")
    val daysStartingWithT = daysOfWeek.filter { it.startsWith("T") }
    for(day in daysStartingWithT){
        println(day)
    }

    println("\nDays containing the letter e:")
    val daysContaininge = daysOfWeek.filter { it.contains("e") }
    for(day in daysContaininge){
        println(day)
    }

    println("\nDays with length 6:")
    val daysWithLength6 = daysOfWeek.filter { it.length == 6 }
    for(day in daysWithLength6){
        println(day)
    }



    //3
    println("\nPrime numbers between 1 and 10:")
    for(num in 1..10){
        if(isPrime(num)){
            println(num)
        }
    }



    //4
    val message = "Sapientia"

    val encodedMessage = messageCoding(message, ::encode)
    println("\nEncoded message: $encodedMessage")

    val decodedMessage = messageCoding(encodedMessage, ::decode)
    println("Decoded message: $decodedMessage")



    //5
    val numbers = listOf(6, 3, 76, 1, 52, 55, 9, 22)

    println("\nEven numbers in the list:")
    printEvenNumbers(numbers)



    //6
    val doubledNumbers = numbers.map { it * 2 }
    println("\nOriginal list: $numbers")
    println("Doubled list: $doubledNumbers")

    val capitalizedDaysOfWeek = daysOfWeek.map { it.uppercase() }
    println("Days of week capitalized: $capitalizedDaysOfWeek")

    val firstCharacters = daysOfWeek.map { it.first().uppercase() }
    println("First characters: $firstCharacters")

    val lengths = daysOfWeek.map { it.length }
    println("Length of days: $lengths")

    val averageLength = lengths.average()
    println("Average length of days: $averageLength")



    //7
    val mutableDaysOfWeek = daysOfWeek.toMutableList()

    mutableDaysOfWeek.removeIf { it.contains('n') }
    println("\nDays that do not contain the letter n: $mutableDaysOfWeek")

    val indexedDays = mutableDaysOfWeek.withIndex()
    indexedDays.forEach { (index, day) -> println("Item at $index is $day") }

    val sortedDays = mutableDaysOfWeek.sorted()
    println("Sorted days: $sortedDays\n")



    //8
    val randList = Array(10) { (0..100).random() }
    println("Random integers:")
    randList.forEach {
        println(it)
    }

    val sortedArray = randList.sortedArray()
    println("\nSorted array in ascending order:")
    sortedArray.forEach {
        println(it)
    }

    val containsEvenNumber = randList.any { it % 2 == 0 }
    val containsOddNumber = randList.any{ it % 2 == 1}

    if (containsEvenNumber) {
        println("The array contains even number/s.")
    } else {
        println("The array does not contain any even number.")
    }

    if(containsOddNumber){
        println("Not all the numbers are even.")
    }
    else{
        println("All the numbers are even.")
    }

    val average = randList.average()
    println("The average of the numbers: $average")

}



fun isPrime(number: Int): Boolean{
    if(number <= 1){
        return false
    }
    for(i in 2..number / 2){
        if(number % i == 0){
            return false
        }
    }
    return true
}

fun encode(message: String, number: Int): String {
    return message.map { c ->
        (c.code + number).toChar()
    }.joinToString("")
}
fun decode(message: String, number: Int): String {
    return message.map { c ->
        (c.code - number).toChar()
    }.joinToString("")
}

fun messageCoding(msg: String, func: (String, Int) -> String): String {
    val shift = 5
    return func(msg, shift)
}

fun printEvenNumbers(numbers: List<Int>) = numbers.filter { it % 2 == 0 }.forEach { println(it) }