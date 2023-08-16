package com.ashehata.orange_task.modules.news.data.repository

import com.ashehata.orange_task.common.presentation.connectivity.NetworkConnectivity
import com.ashehata.orange_task.modules.news.data.local.source.NewsLocalDataSource
import com.ashehata.orange_task.modules.news.data.model.NewsDataModel
import com.ashehata.orange_task.modules.news.data.remote.NewsRemoteDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import java.io.IOException

class NewsRepositoryImplTest {

    @Mock
    private lateinit var newsLocalDataSource: NewsLocalDataSource

    @Mock
    private lateinit var newsRemoteDataSource: NewsRemoteDataSource

    @Mock
    private lateinit var networkConnectivity: NetworkConnectivity

    private lateinit var newsRepository: NewsRepositoryImpl

    private val newsList = List(20) { NewsDataModel(url = "dummy", title = "apple company") }

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @Before
    fun setup() {
        newsRepository = NewsRepositoryImpl(
            local = newsLocalDataSource,
            remote = newsRemoteDataSource,
            networkConnectivity = networkConnectivity
        )
    }

    @Test
    fun `getNews should return local news when network is not available`() = runBlocking {
        // arrange
        val page = 1
        val perPage = 20
        val keyword = "apple"


        // mocking
        Mockito.`when`(networkConnectivity.isNetworkAvailable()).thenReturn(false)

        Mockito.`when`(
            newsLocalDataSource.getAllNews(
                page = anyInt(),
                perPage = anyInt(),
                keyword = anyString()
            )
        ).thenReturn(newsList)

        // act
        val actual = newsRepository.getNews(
            page = page,
            perPage = perPage,
            keyword = keyword
        )

        // assertion
        verify(newsLocalDataSource, never()).clearAllNews()
        verify(newsRemoteDataSource, never()).getNews(
            page = page,
            perPage = perPage,
            keyword = keyword
        )
        assertEquals(newsList.size, actual.size)
    }

    @Test
    fun `getNews from cache after clear cache when network available and first page`() =
        runBlocking {
            // arrange
            val page = 1
            val perPage = 20
            val keyword = "apple"

            // mocking
            Mockito.`when`(networkConnectivity.isNetworkAvailable()).thenReturn(true)

            Mockito.`when`(
                newsLocalDataSource.getAllNews(
                    page = anyInt(),
                    perPage = anyInt(),
                    keyword = anyString()
                )
            ).thenReturn(newsList)

            // act
            val actual = newsRepository.getNews(
                page = page,
                perPage = perPage,
                keyword = keyword
            )

            // assertion
            Mockito.verify(newsLocalDataSource).clearAllNews()
            Mockito.verify(newsRemoteDataSource).getNews(
                page = page,
                perPage = perPage,
                keyword = keyword
            )
            assertEquals(newsList.size, actual.size)
        }


    @Test
    fun `getNews from cache when network available and any page except first`() = runBlocking {
        // arrange
        val page = 20
        val perPage = 20
        val keyword = "apple"

        // mocking
        Mockito.`when`(networkConnectivity.isNetworkAvailable()).thenReturn(true)

        Mockito.`when`(
            newsLocalDataSource.getAllNews(
                page = anyInt(),
                perPage = anyInt(),
                keyword = anyString()
            )
        ).thenReturn(newsList)

        Mockito.`when`(
            newsRemoteDataSource.getNews(
                page = anyInt(),
                perPage = anyInt(),
                keyword = anyString()
            )
        ).thenReturn(newsList)

        // act
        val actual = newsRepository.getNews(
            page = page,
            perPage = perPage,
            keyword = keyword
        )

        // assertion
        assertEquals(newsList.size, actual.size)
    }

    @Test(expected = IOException::class)
    fun `getNews should throw IOException when local and remote data are empty and network is not available`() =
        runBlocking {
            // arrange
            val page = 20
            val perPage = 20
            val keyword = "apple"


            `when`(networkConnectivity.isNetworkAvailable()).thenReturn(false)
            `when`(newsLocalDataSource.getAllNews(anyInt(), anyInt(), anyString())).thenReturn(
                emptyList()
            )
            newsRepository.getNews(
                page = page,
                perPage = perPage,
                keyword = keyword
            )
            return@runBlocking
        }


}