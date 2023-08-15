package com.ashehata.orange_task.modules.news.data.repository

import com.ashehata.orange_task.common.presentation.connectivity.NetworkConnectivity
import com.ashehata.orange_task.modules.news.data.local.source.NewsLocalDataSource
import com.ashehata.orange_task.modules.news.data.mapper.toDomainModel
import com.ashehata.orange_task.modules.news.data.remote.NewsRemoteDataSource
import com.ashehata.orange_task.modules.news.domain.model.NewsDomainModel
import com.ashehata.orange_task.modules.news.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(
    private val remote: NewsRemoteDataSource,
    private val local: NewsLocalDataSource,
    private val networkConnectivity: NetworkConnectivity
) : NewsRepository {


    override suspend fun getNews(
        page: Int,
        perPage: Int,
        keyword: String
    ): List<NewsDomainModel> = withContext(Dispatchers.IO) {
        if (networkConnectivity.isNetworkAvailable()) {
            if (page == 1)
                local.clearAllNews()

            val remoteNews = remote.getNews(page, perPage, keyword)
            local.insertNews(remoteNews)
        }

        val localNews = local.getAllNews(page = page, perPage = perPage, keyword = keyword)
            .map { it.toDomainModel() }
        // Hint: Throw IOException to notify the UI (to display ERROR view) becase there is some data but now local data is empty
        if (localNews.isEmpty() && networkConnectivity.isNetworkAvailable()
                .not()
        ) throw IOException()
        return@withContext localNews

    }


}