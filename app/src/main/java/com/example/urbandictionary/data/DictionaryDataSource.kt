package com.example.urbandictionary.data

import com.example.urbandictionary.`interface`.DictionaryRest
import com.example.urbandictionary.model.Search

/**
 * @author harishrevuri created on March 18th 2020
 */
class DictionaryDataSource(private val restInterface: DictionaryRest) {

    fun searchDictionary(string: String): ArrayList<Search>? {
        try {
            return restInterface.searchDictionary(string)?.list
        } catch (e: Exception) {
            e.stackTrace
        }
        return null
    }
}