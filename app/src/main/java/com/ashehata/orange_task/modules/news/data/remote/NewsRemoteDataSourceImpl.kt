package com.ashehata.orange_task.modules.news.data.remote

import com.ashehata.orange_task.modules.news.data.model.NewsDataModel
import com.ashehata.orange_task.modules.news.data.retrofit.service.NewsService
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val service: NewsService
) : NewsRemoteDataSource {

    override suspend fun getArticles(page: Int, perPage: Int): List<NewsDataModel> {
        return service.getArticles(page, perPage).articles ?: emptyList()
    }
}