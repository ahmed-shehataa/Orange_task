package com.ashehata.orange_task.modules.news.data.remote

import android.util.Log
import com.ashehata.orange_task.modules.news.data.model.NewsDataModel
import com.ashehata.orange_task.modules.news.data.model.SourceDataModel
import com.ashehata.orange_task.modules.news.data.retrofit.service.NewsService
import kotlinx.coroutines.delay
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val service: NewsService
) : NewsRemoteDataSource {

    override suspend fun getNews(page: Int, perPage: Int, keyword: String): List<NewsDataModel> {
        return service.getNews(page, perPage, keyword).articles ?: emptyList()
        //return fakeResponse(page)
    }

    private suspend fun fakeResponse(page: Int): List<NewsDataModel> {
        delay(2000)
        return List(20) {
            val randomKey = it * page * 2
            Log.i("fakeResponse", "fakeResponse: $randomKey")
            NewsDataModel(
                url = (randomKey).toString(),
                author = "$randomKey + author",
                content = "$randomKey + content",
                description = "$randomKey + description",
                publishedAt = "publishedAt",
                source = SourceDataModel(
                    id = null,
                    name = randomKey.toString()
                ),
                title = "$page apple",
                urlToImage = null
            )
        }
    }
}