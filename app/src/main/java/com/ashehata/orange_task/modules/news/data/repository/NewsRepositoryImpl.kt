package com.ashehata.orange_task.modules.news.data.repository

import com.ashehata.orange_task.modules.news.data.mapper.toDomainModel
import com.ashehata.orange_task.modules.news.data.remote.NewsRemoteDataSource
import com.ashehata.orange_task.modules.news.domain.model.NewsDomainModel
import com.ashehata.orange_task.modules.news.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remote: NewsRemoteDataSource,
) : NewsRepository {


    override suspend fun getArticles(page: Int, perPage: Int): List<NewsDomainModel> {
        return remote.getArticles(page, perPage).map { it.toDomainModel() }
    }


}