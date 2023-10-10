package main.dictionary.models

import main.dictionary.interfaces.IDictionary
import java.io.File
import java.util.TreeSet

object TreeSetDictionary : IDictionary {

    private val words = TreeSet<String>()

    init {
        File(IDictionary.DICTIONARY_FILE).forEachLine { words.add(it) }
    }

    override fun add(word: String): Boolean {
        if (!words.contains(word)) {
            words.add(word)
            return true
        }
        return false
    }

    override fun find(word: String): Boolean {
        return words.contains(word)
    }

    override fun size(): Int {
        return words.size
    }
}
