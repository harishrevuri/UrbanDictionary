package com.example.urbandictionary

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.urbandictionary.`interface`.DictionaryRest
import com.example.urbandictionary.`interface`.DictionarySearch
import com.example.urbandictionary.data.DictionaryDataSource
import com.example.urbandictionary.model.SearchList
import com.example.urbandictionary.viewModel.DictionaryViewModel
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.mockito.Mockito
import java.io.File
import java.nio.file.Files

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    companion object {
        private const val GRADLE_STATIC_RELATIVE_PATH = "src/test/"
    }

    private lateinit var viewModel: DictionaryViewModel
    private lateinit var restInterface: DictionaryRest

    @Before
    fun setUp() {
        restInterface = Mockito.mock(DictionarySearch::class.java)
        val dataSource = DictionaryDataSource(restInterface)
        viewModel = DictionaryViewModel(dataSource)
    }

    @Test
    fun searchDictionary() {
        val configJSON = parseLocalFile("resources/response_200.json")
        val fromJson = Gson().fromJson(configJSON, SearchList::class.java)

        val string = "world"

        Mockito.`when`(restInterface.searchDictionary(string)).thenReturn(fromJson)

        runBlocking {
            viewModel.searchDictionary(string)
        }
        // there is an issue with coroutines unit testing - need to fix it
        viewModel.searchResultLiveDate.observeForever {
            Assert.assertTrue(it?.size == 10)
        }
    }

    private fun parseLocalFile(fileName: String): String {
        val classLoader = this.javaClass.classLoader
        val resource = classLoader!!.getResource(fileName)
        val file = File(if (resource != null) resource.path else GRADLE_STATIC_RELATIVE_PATH + fileName)
        return String(Files.readAllBytes(file.toPath()), Charsets.UTF_8)
    }
}
