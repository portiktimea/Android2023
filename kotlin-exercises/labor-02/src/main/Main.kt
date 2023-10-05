package main

import main.dictionary.interfaces.IDictionary
import main.dictionary.providers.DictionaryProvider
import main.dictionary.providers.DictionaryType
import javax.swing.JPopupMenu.Separator

fun main(args: Array<String>) {

    val name = "Bonta Johanna-Timea"
    println(name.monogram())

    val list = listOf("apple", "pear", "melon", "strawberry")
    val separator = "#"

    println(list.joinBySeparator(separator))
    println(list.longestString())

    val dict: IDictionary = DictionaryProvider.createDictionary(DictionaryType.ARRAY_LIST)
    println("Number of words: ${dict.size()}")
    var word: String?
    while(true){
        print("What to find? ")
        word = readLine()
        if( word.equals("quit")){
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }

}

fun String.monogram() = split(" ", "-").map { it.first() }.joinToString("")
fun List<String>.joinBySeparator(separator: String) = joinToString(separator = separator)
fun List<String>.longestString() = maxByOrNull { it.length }