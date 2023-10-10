package main.dictionary

import main.dictionary.interfaces.IDictionary
import main.dictionary.providers.DictionaryProvider
import main.dictionary.providers.DictionaryType

fun main(args: Array<String>) {


    val dict: IDictionary = DictionaryProvider.createDictionary(DictionaryType.HASH_SET)
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
