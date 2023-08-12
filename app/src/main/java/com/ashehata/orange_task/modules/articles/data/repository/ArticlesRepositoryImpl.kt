package com.ashehata.orange_task.modules.articles.data.repository

import com.ashehata.orange_task.modules.articles.data.mapper.toDomainModel
import com.ashehata.orange_task.modules.articles.data.remote.ArticlesRemoteDataSource
import com.ashehata.orange_task.modules.articles.domain.model.ArticleDomainModel
import com.ashehata.orange_task.modules.articles.domain.repository.ArticlesRepository
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(
    private val remote: ArticlesRemoteDataSource,
) : ArticlesRepository {


    override suspend fun getArticles(page: Int, perPage: Int): List<ArticleDomainModel> {
        return remote.getArticles(page, perPage).map { it.toDomainModel() }
    }


}