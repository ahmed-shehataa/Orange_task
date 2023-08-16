package com.ashehata.orange_task.modules.news.domain.usecase

import com.ashehata.orange_task.modules.news.domain.model.NewsDomainModel
import com.ashehata.orange_task.modules.news.domain.repository.NewsRepository
import com.ashehata.orange_task.util.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    suspend fun execute(
        page: Int = 1,
        perPage: Int,
        keyword: String,
    ): List<NewsDomainModel> {
        return repository.getNews(page, perPage, keyword)
    }
}