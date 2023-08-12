package com.ashehata.orange_task.modules.news.data.retrofit.service

import com.ashehata.orange_task.common.data.retrofit.ApiPaths
import com.ashehata.orange_task.common.data.retrofit.Query.Companion.API_KEY
import com.ashehata.orange_task.common.data.retrofit.Query.Companion.COMPANY
import com.ashehata.orange_task.common.data.retrofit.Query.Companion.PAGE
import com.ashehata.orange_task.common.data.retrofit.Query.Companion.PER_PAGE
import com.ashehata.orange_task.common.data.retrofit.Query.Companion.SORTED_BY
import com.ashehata.orange_task.modules.news.data.retrofit.response.NewsResponse
import com.ashehata.orange_task.util.Constants.API_KEY_VALUE
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsService {

    @GET(ApiPaths.ARTICLES)
    suspend fun getArticles(
        @Query(PAGE) page: Int,
        @Query(PER_PAGE) perPage: Int,
        @Query(COMPANY) company: String = "apple",
        @Query(SORTED_BY) sortBy: String = "publishedAt",
        @Query(API_KEY) apiKey: String = API_KEY_VALUE,
    ): NewsResponse
}