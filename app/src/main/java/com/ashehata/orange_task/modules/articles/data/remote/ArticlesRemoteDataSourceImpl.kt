package com.ashehata.orange_task.modules.articles.data.remote

import com.ashehata.orange_task.modules.articles.data.model.ArticleDataModel
import com.ashehata.orange_task.modules.articles.data.retrofit.service.ArticlesService
import javax.inject.Inject

class ArticlesRemoteDataSourceImpl @Inject constructor(
    private val service: ArticlesService
) : ArticlesRemoteDataSource {

    override suspend fun getArticles(page: Int, perPage: Int): List<ArticleDataModel> {
        return service.getArticles(page, perPage).articles ?: emptyList()
    }
}