package com.example.urbandictionary.util

import com.example.urbandictionary.`interface`.DictionarySearch
import com.example.urbandictionary.data.DictionaryDataSource

/**
 * @author harishrevuri created on March 18th 2020
 */
class DataSourceProvider private constructor() {

    companion object {
        @Volatile
        private var DICTIONARY_DATA_SOURCE: DictionaryDataSource? = null

        fun getDictionaryInstance(): DictionaryDataSource {
            return DICTIONARY_DATA_SOURCE ?: synchronized(this) {
                DictionaryDataSource(DictionarySearch()).also {
                    DICTIONARY_DATA_SOURCE = it
                }
            }
        }
    }
}