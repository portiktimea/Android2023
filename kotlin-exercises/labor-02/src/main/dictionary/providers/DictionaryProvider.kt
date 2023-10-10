package main.dictionary.providers

import main.dictionary.interfaces.IDictionary
import main.dictionary.models.HashSetDictionary
import main.dictionary.models.ListDictionary
import main.dictionary.models.TreeSetDictionary

class DictionaryProvider {

    companion object{
        fun createDictionary(type : DictionaryType) : IDictionary =
            when(type){
                DictionaryType.ARRAY_LIST -> ListDictionary
                DictionaryType.HASH_SET -> HashSetDictionary
                DictionaryType.TREE_SET -> TreeSetDictionary
            }
    }
}