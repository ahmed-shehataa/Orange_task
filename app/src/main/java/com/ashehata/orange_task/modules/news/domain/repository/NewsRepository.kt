package com.ashehata.orange_task.modules.news.domain.repository

import com.ashehata.orange_task.modules.news.domain.model.NewsDomainModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNews(page: Int, perPage: Int, keyword: String): List<NewsDomainModel>
}