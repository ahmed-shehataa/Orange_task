package com.ashehata.orange_task.modules.news.domain.usecase

import com.ashehata.orange_task.modules.news.domain.model.NewsDomainModel
import com.ashehata.orange_task.modules.news.domain.repository.NewsRepository
import com.ashehata.orange_task.util.Constants
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class GetNewsUseCaseTest {

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var mockRepository: NewsRepository

    private lateinit var getNewsUseCase: GetNewsUseCase

    private val newsList = List(10) { NewsDomainModel(url = "dummy", title = "apple company") }

    @Before
    fun setup() {
        getNewsUseCase = GetNewsUseCase(mockRepository)
    }

    @Test
    fun `execute should call repository getNews method with correct arguments`() = runBlocking {
        // arrange
        val page = 1
        val perPage = Constants.PAGE_SIZE
        val keyword = "apple"

        `when`(mockRepository.getNews(page, perPage, keyword)).thenReturn(newsList)

        // act
        val result = getNewsUseCase.execute(page, perPage, keyword)

        // assert
        verify(mockRepository).getNews(page, perPage, keyword)
        assertEquals(newsList, result)
    }
}