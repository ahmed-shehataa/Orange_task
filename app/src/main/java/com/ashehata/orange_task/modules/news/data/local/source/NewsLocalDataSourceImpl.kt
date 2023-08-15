package com.ashehata.orange_task.modules.news.data.local.source

import com.ashehata.orange_task.modules.news.data.local.dao.NewsDao
import com.ashehata.orange_task.modules.news.data.model.NewsDataModel
import javax.inject.Inject

class NewsLocalDataSourceImpl @Inject constructor(
    private val dao: NewsDao
) : NewsLocalDataSource {
    override fun getAllNews(page: Int, perPage: Int, keyword: String): List<NewsDataModel> {
        return dao.getAll(page, perPage, keyword)
    }

    override suspend fun insertNews(newsList: List<NewsDataModel>) {
        dao.insertAll(newsList)
    }

    override suspend fun deleteNews(url: String) {
        dao.delete(url)
    }

    override suspend fun clearAllNews() {
        dao.deleteAll()
    }


}