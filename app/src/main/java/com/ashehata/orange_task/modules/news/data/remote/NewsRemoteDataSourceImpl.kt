package com.ashehata.orange_task.modules.news.data.remote

import com.ashehata.orange_task.modules.news.data.model.NewsDataModel
import com.ashehata.orange_task.modules.news.data.retrofit.service.NewsService
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val service: NewsService
) : NewsRemoteDataSource {

    override suspend fun getNews(page: Int, perPage: Int, keyword: String): List<NewsDataModel> {
        return service.getNews(page, perPage, keyword).articles ?: emptyList()
    }
}