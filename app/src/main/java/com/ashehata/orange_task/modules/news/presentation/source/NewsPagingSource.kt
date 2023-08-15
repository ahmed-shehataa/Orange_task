package com.ashehata.orange_task.modules.news.presentation.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ashehata.orange_task.modules.news.domain.usecase.GetNewsUseCase
import com.ashehata.orange_task.modules.news.presentation.mapper.toUIModel
import com.ashehata.orange_task.modules.news.presentation.model.NewsUIModel

class NewsPagingSource(
    private val getNewsUseCase: GetNewsUseCase,
    private val keyword: String
) : PagingSource<Int, NewsUIModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsUIModel> {
        return try {
            val page = params.key ?: 1
            Log.i("NewsPagingSource", "load_page: $page")

            val response =
                getNewsUseCase.execute(page = page, keyword = keyword).map { it.toUIModel() }
            Log.i("NewsPagingSource", response.isEmpty().toString())

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            Log.e("NewsPagingSource", "Error loading page:", e)
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsUIModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            return anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}