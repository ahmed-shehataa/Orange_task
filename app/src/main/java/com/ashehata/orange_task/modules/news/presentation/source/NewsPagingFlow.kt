package com.ashehata.orange_task.modules.news.presentation.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ashehata.orange_task.modules.news.domain.usecase.GetNewsUseCase
import com.ashehata.orange_task.modules.news.presentation.model.NewsUIModel
import com.ashehata.orange_task.util.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsPagingFlow @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) {
    fun getNewsFlow(keyword: String): Flow<PagingData<NewsUIModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
            ),
            pagingSourceFactory = {
                NewsPagingSource(getNewsUseCase, keyword)
            },
        ).flow
    }
}