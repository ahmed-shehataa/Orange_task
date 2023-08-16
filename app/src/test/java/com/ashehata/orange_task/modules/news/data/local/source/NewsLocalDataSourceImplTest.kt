package com.ashehata.orange_task.modules.news.data.local.source

import com.ashehata.orange_task.modules.news.data.local.dao.NewsDao
import com.ashehata.orange_task.modules.news.data.model.NewsDataModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.times

class NewsLocalDataSourceImplTest {

    private val newsList = List(20) { NewsDataModel(url = "dummy", title = "apple company") }
    private var newsDao: NewsDao = Mockito.mock()
    private var newsLocalDataSourceImpl = NewsLocalDataSourceImpl(newsDao)

    @Test
    fun `getAllNews()`() {
        Mockito.`when`(newsDao.getAll(1, 20, "apple")).thenReturn(newsList)
        val actual = newsLocalDataSourceImpl.getAllNews(1, 20, "apple")
        assertEquals(newsList, actual)
    }


    @Test
    fun `insertNews()`() = runBlocking {
        newsLocalDataSourceImpl.insertNews(newsList)
        Mockito.verify(newsDao, times(1)).insertAll(newsList)
    }

    @Test
    fun `deleteNews()`() = runBlocking {
        val news = NewsDataModel(url = "dummy", title = "apple company")
        newsLocalDataSourceImpl.deleteNews(news.url)
        Mockito.verify(newsDao, times(1)).delete(news.url)
    }

    @Test
    fun `clearAllNews()`() = runBlocking {
        newsLocalDataSourceImpl.clearAllNews()
        Mockito.verify(newsDao, times(1)).deleteAll()
    }




}