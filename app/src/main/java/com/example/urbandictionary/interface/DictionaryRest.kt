package com.example.urbandictionary.`interface`

import com.example.urbandictionary.model.SearchList

/**
 * @author harishrevuri created on March 18th 2020
 */
interface DictionaryRest {
    fun searchDictionary(string: String): SearchList?
}