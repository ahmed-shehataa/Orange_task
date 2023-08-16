package com.ashehata.orange_task.modules.news.presentation.source

import androidx.paging.PagingSource
import com.ashehata.orange_task.modules.news.domain.model.NewsDomainModel
import com.ashehata.orange_task.modules.news.domain.usecase.GetNewsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.anyString
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class NewsPagingSourceTest {

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var mockGetNewsUseCase: GetNewsUseCase

    private lateinit var newsPagingSource: NewsPagingSource

    private val newsList = List(10) { NewsDomainModel(url = "dummy", title = "apple company") }

    @Before
    fun setup() {
        newsPagingSource = NewsPagingSource(mockGetNewsUseCase, "apple", 20)
    }

    @Test
    fun `load first page should call getNewsUseCase execute method with correct arguments`() =
        runBlocking {
            // arrange
            val loadParams: PagingSource.LoadParams.Refresh<Int> =
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 20,
                    placeholdersEnabled = false
                )

            `when`(mockGetNewsUseCase.execute(anyInt(), anyInt(), anyString())).thenReturn(newsList)

            // act
            newsPagingSource.load(loadParams)

            // assert
            verify(mockGetNewsUseCase).execute(1, 20, "apple")
            return@runBlocking
        }


    @Test
    fun `load next page should call getNewsUseCase execute method with correct arguments`() =
        runBlocking {
            // arrange
            val loadParams =
                PagingSource.LoadParams.Refresh(
                    key = 20,
                    loadSize = 20,
                    placeholdersEnabled = false
                )

            `when`(mockGetNewsUseCase.execute(anyInt(), anyInt(), anyString())).thenReturn(newsList)

            // act
            newsPagingSource.load(loadParams)

            // assert
            verify(mockGetNewsUseCase).execute(20, 20, "apple")
            return@runBlocking
        }


    @Test
    fun `load last page should call getNewsUseCase execute method with correct arguments`() =
        runBlocking {
            // arrange
            val loadParams =
                PagingSource.LoadParams.Refresh(
                    key = 20,
                    loadSize = 20,
                    placeholdersEnabled = false
                )

            `when`(
                mockGetNewsUseCase.execute(
                    anyInt(),
                    anyInt(),
                    anyString()
                )
            ).thenReturn(emptyList())

            // act
            val result = newsPagingSource.load(loadParams)

            val expected = PagingSource.LoadResult.Page(
                data = emptyList(),
                prevKey = 19,
                nextKey = null,
            )

            // assert
            verify(mockGetNewsUseCase).execute(20, 20, "apple")
            assertEquals(expected, result)

        }


}