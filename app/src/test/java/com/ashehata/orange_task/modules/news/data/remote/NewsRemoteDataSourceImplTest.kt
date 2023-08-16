package com.ashehata.orange_task.modules.news.data.remote

import com.ashehata.orange_task.modules.news.data.model.NewsDataModel
import com.ashehata.orange_task.modules.news.data.retrofit.response.NewsResponse
import com.ashehata.orange_task.modules.news.data.retrofit.service.NewsService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class NewsRemoteDataSourceImplTest {

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var newsService: NewsService

    private lateinit var newsRemoteDataSource: NewsRemoteDataSourceImpl

    private val newsList = List(20) { NewsDataModel(url = "dummy", title = "apple company") }


    @Before
    fun setup() {
        newsRemoteDataSource = NewsRemoteDataSourceImpl(newsService)
    }

    @Test
    fun `getNews first page`() = runBlocking {
        val response =
            NewsResponse(articles = newsList, status = "success", totalResults = newsList.size)

        `when`(
            newsService.getNews(
                page = anyInt(),
                perPage = anyInt(),
                keyword = anyString(),
                searchIn = anyString(),
                sortBy = anyString(),
                apiKey = anyString()
            )
        ).thenReturn(response)

        val actual = newsRemoteDataSource.getNews(1, 20, "apple")
        assertEquals(newsList, actual)
    }

    @Test
    fun `getNews last page`() = runBlocking {
        val newsLastList = newsList.take(5)
        val currentPage = 20
        val perPage = 20
        val keyword = "apple"
        val response =
            NewsResponse(
                articles = newsLastList,
                status = "success",
                totalResults = newsLastList.size
            )

        `when`(
            newsService.getNews(
                page = anyInt(),
                perPage = anyInt(),
                keyword = anyString(),
                searchIn = anyString(),
                sortBy = anyString(),
                apiKey = anyString()
            )
        ).thenReturn(response)
        val actual = newsRemoteDataSource.getNews(currentPage, perPage, keyword)
        assertEquals(newsLastList, actual)
    }

    @Test
    fun `getNews page with empty list`() = runBlocking {
        val newsLastList = emptyList<NewsDataModel>()
        val currentPage = 20
        val perPage = 20
        val keyword = "apple"
        val response =
            NewsResponse(articles = newsLastList, status = "success", totalResults = 0)

        `when`(
            newsService.getNews(
                page = anyInt(),
                perPage = anyInt(),
                keyword = anyString(),
                searchIn = anyString(),
                sortBy = anyString(),
                apiKey = anyString()
            )
        ).thenReturn(response)

        val actual = newsRemoteDataSource.getNews(currentPage, perPage, keyword)
        assertEquals(newsLastList.size, actual.size)
    }
}