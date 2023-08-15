package com.ashehata.orange_task.modules.news.data.local.source

import com.ashehata.orange_task.modules.news.data.model.NewsDataModel
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {

    fun getAllNews(page: Int, perPage: Int, keyword: String): List<NewsDataModel>

    suspend fun insertNews(newsList: List<NewsDataModel>)

    suspend fun deleteNews(url: String)

    suspend fun clearAllNews()

}