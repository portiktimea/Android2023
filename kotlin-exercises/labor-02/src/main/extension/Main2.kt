package main.extension

fun main(args: Array<String>) {

    val name = "Bonta Johanna-Timea"
    println(name.monogram())

    val list = listOf("apple", "pear", "melon", "strawberry")
    val separator = "#"

    println(list.joinBySeparator(separator))
    println("Longest string: " + list.longestString())

}

fun String.monogram() = split(" ", "-").map { it.first() }.joinToString("")
fun List<String>.joinBySeparator(separator: String) = joinToString(separator = separator)
fun List<String>.longestString() = maxByOrNull { it.length }
