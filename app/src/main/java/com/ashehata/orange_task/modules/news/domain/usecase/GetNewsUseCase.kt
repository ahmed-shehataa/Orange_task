package com.ashehata.orange_task.modules.news.domain.usecase

import com.ashehata.orange_task.modules.news.domain.model.NewsDomainModel
import com.ashehata.orange_task.modules.news.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {

    suspend fun execute(
        page: Int = 1,
        perPage: Int = 20,
    ): List<NewsDomainModel> {
        return repository.getArticles(page, perPage)
    }
}