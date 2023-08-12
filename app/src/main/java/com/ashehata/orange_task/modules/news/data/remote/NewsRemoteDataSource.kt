package com.ashehata.orange_task.modules.news.data.remote

import com.ashehata.orange_task.modules.news.data.model.NewsDataModel

interface NewsRemoteDataSource {

    suspend fun getArticles(page: Int, perPage: Int): List<NewsDataModel>
}