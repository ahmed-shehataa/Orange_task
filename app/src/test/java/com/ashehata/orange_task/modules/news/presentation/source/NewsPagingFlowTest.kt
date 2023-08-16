package com.ashehata.orange_task.modules.news.presentation.source

import androidx.paging.PagingData
import androidx.paging.map
import com.ashehata.orange_task.modules.news.domain.model.NewsDomainModel
import com.ashehata.orange_task.modules.news.domain.usecase.GetNewsUseCase
import com.ashehata.orange_task.modules.news.presentation.mapper.toUIModel
import com.ashehata.orange_task.modules.news.presentation.model.NewsUIModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@RunWith(MockitoJUnitRunner::class)
class NewsPagingFlowTest {

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var mockGetNewsUseCase: GetNewsUseCase

    private lateinit var newsPagingFlow: NewsPagingFlow

    private val newsList = List(10) { NewsDomainModel(url = "dummy", title = "apple company") }

    @Before
    fun setup() {
        newsPagingFlow = NewsPagingFlow(mockGetNewsUseCase)
    }

    /**
     * TODO Not working yet
     */
    @Test
    fun `getNewsFlow should return expected PagingData`() = runBlocking {
        // arrange
        val keyword = "apple"
        val pageSize = 20
        val newsUIList = newsList.map { it.toUIModel() }

        Mockito.`when`(mockGetNewsUseCase.execute(anyInt(), anyInt(), anyString()))
            .thenReturn(newsList)

        // act
        val pagingDataFlow = newsPagingFlow.getNewsFlow(keyword, pageSize)

        // assert
        pagingDataFlow.collect { pagingData ->
            val collectedItems = pagingData.map { it }

            val mutList = mutableListOf<NewsUIModel>()
            collectedItems.map {
                mutList.add(it)
            }
            // Assert that the collected items match the expected newsUIList
            assertEquals(mutList.toList(), newsUIList)
        }

    }
}