package com.ashehata.orange_task.modules.news.data.local.source

import com.ashehata.orange_task.modules.news.data.local.dao.NewsDao
import com.ashehata.orange_task.modules.news.data.model.NewsDataModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.verify

class NewsLocalDataSourceImplTest {


    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var newsDao: NewsDao

    private lateinit var newsLocalDataSource: NewsLocalDataSourceImpl

    private val newsList = List(20) { NewsDataModel(url = "dummy", title = "apple company") }

    @Before
    fun setup() {
        newsLocalDataSource = NewsLocalDataSourceImpl(newsDao)
    }

    @Test
    fun `getAllNews should call dao getAll method with correct arguments`() {
        // arrange
        val page = 1
        val perPage = 10
        val keyword = "apple"

        // act
        newsLocalDataSource.getAllNews(page, perPage, keyword)

        // assert
        verify(newsDao).getAll(page, perPage, keyword)
    }

    @Test
    fun `insertNews should call dao insertAll method with correct arguments`() = runBlocking {
        // act
        newsLocalDataSource.insertNews(newsList)

        // assert
        verify(newsDao).insertAll(newsList)
    }


    @Test
    fun `deleteNews should call dao delete method with correct argument`() = runBlocking {
        // arrange
        val urlToDelete = "dummy"

        // act
        newsLocalDataSource.deleteNews(urlToDelete)

        // assert
        verify(newsDao).delete(urlToDelete)
    }

    @Test
    fun `clearAllNews should call dao deleteAll method`() = runBlocking {
        // act
        newsLocalDataSource.clearAllNews()

        // assert
        verify(newsDao).deleteAll()
    }


}