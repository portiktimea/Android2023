package main.dictionary.providers

import main.dictionary.interfaces.IDictionary
import main.dictionary.models.ListDictionary

enum class DictionaryType{
    ARRAY_LIST,
    HASH_SET,
    TREE_SET
}
class DictionaryProvider {

    companion object{
        fun createDictionary(type : DictionaryType) : IDictionary =
            when(type){
                DictionaryType.ARRAY_LIST -> ListDictionary
                DictionaryType.HASH_SET -> TODO()
                DictionaryType.TREE_SET -> TODO()
            }
    }
}