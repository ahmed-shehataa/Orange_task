package com.ashehata.orange_task.modules.articles.domain.usecase

import com.ashehata.orange_task.modules.articles.domain.model.ArticleDomainModel
import com.ashehata.orange_task.modules.articles.domain.repository.ArticlesRepository
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val repository: ArticlesRepository
) {

    suspend fun execute(
        page: Int = 1,
        perPage: Int = 20,
    ): List<ArticleDomainModel> {
        return repository.getArticles(page, perPage)
    }
}