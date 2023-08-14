package com.ashehata.orange_task.modules.news.data.remote

import com.ashehata.orange_task.modules.news.data.model.NewsDataModel

interface NewsRemoteDataSource {

    suspend fun getNews(page: Int, perPage: Int, keyword: String): List<NewsDataModel>
}