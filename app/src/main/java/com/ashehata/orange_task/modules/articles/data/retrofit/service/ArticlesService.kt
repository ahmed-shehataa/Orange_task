package com.ashehata.orange_task.modules.articles.data.retrofit.service

import com.ashehata.orange_task.common.data.retrofit.ApiPaths
import com.ashehata.orange_task.common.data.retrofit.Query.Companion.COMPANY
import com.ashehata.orange_task.common.data.retrofit.Query.Companion.PAGE
import com.ashehata.orange_task.common.data.retrofit.Query.Companion.PER_PAGE
import com.ashehata.orange_task.common.data.retrofit.Query.Companion.SORTED_BY
import com.ashehata.orange_task.modules.articles.data.retrofit.response.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ArticlesService {

    @GET(ApiPaths.ARTICLES)
    suspend fun getArticles(
        @Query(PAGE) page: Int,
        @Query(PER_PAGE) perPage: Int,
        @Query(COMPANY) company: String = "apple",
        @Query(SORTED_BY) sortBy: String = "publishedAt",
    ): ArticlesResponse
}