package com.ashehata.orange_task.modules.articles.data.remote

import com.ashehata.orange_task.modules.articles.data.model.ArticleDataModel

interface ArticlesRemoteDataSource {

    suspend fun getArticles(page: Int, perPage: Int): List<ArticleDataModel>
}