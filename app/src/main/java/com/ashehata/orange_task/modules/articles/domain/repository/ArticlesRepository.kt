package com.ashehata.orange_task.modules.articles.domain.repository

import com.ashehata.orange_task.modules.articles.domain.model.ArticleDomainModel

interface ArticlesRepository {

    suspend fun getArticles(page: Int, perPage: Int): List<ArticleDomainModel>
}