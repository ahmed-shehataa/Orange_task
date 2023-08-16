package com.ashehata.orange_task.modules.news.data.remote

import com.ashehata.orange_task.modules.news.data.model.NewsDataModel
import com.ashehata.orange_task.modules.news.data.retrofit.response.NewsResponse
import com.ashehata.orange_task.modules.news.data.retrofit.service.NewsService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito

class NewsRemoteDataSourceImplTest {

    private val newsList = List(20) { NewsDataModel(url = "dummy", title = "apple company") }
    private val newsService: NewsService = Mockito.mock()
    private val newsRemoteDataSourceImpl = NewsRemoteDataSourceImpl(newsService)
    private val perPage = 20

    @Test
    fun `getNews first page`() = runBlocking {
        val response =
            NewsResponse(articles = newsList, status = "success", totalResults = newsList.size)

        Mockito.`when`(newsService.getNews(1, 20, "apple")).thenReturn(response)
        val actual = newsRemoteDataSourceImpl.getNews(1, 20, "apple")
        assertEquals(newsList, actual)
    }

    @Test
    fun `getNews last page`() = runBlocking {
        val newsLastList = newsList.take(5)
        val currentPage = 20
        val keyword = "apple"
        val response =
            NewsResponse(articles = newsLastList, status = "success", totalResults = newsLastList.size)

        Mockito.`when`(newsService.getNews(currentPage, perPage, keyword)).thenReturn(response)
        val actual = newsRemoteDataSourceImpl.getNews(currentPage, perPage, keyword)
        assertEquals(newsLastList, actual)
    }

    @Test
    fun `getNews page with empty list`() = runBlocking {
        val newsLastList = emptyList<NewsDataModel>()
        val currentPage = 20
        val keyword = "apple"
        val response =
            NewsResponse(articles = newsLastList, status = "success", totalResults = 0)

        Mockito.`when`(newsService.getNews(currentPage, perPage, keyword)).thenReturn(response)
        val actual = newsRemoteDataSourceImpl.getNews(currentPage, perPage, keyword)
        assertEquals(newsLastList.size, actual.size)
    }
}