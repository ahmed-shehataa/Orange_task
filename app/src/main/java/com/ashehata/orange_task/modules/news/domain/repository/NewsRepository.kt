package com.ashehata.orange_task.modules.news.domain.repository

import com.ashehata.orange_task.modules.news.domain.model.NewsDomainModel

interface NewsRepository {

    suspend fun getArticles(page: Int, perPage: Int): List<NewsDomainModel>
}