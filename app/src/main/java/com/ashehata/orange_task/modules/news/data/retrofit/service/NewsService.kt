package com.ashehata.orange_task.modules.news.data.retrofit.service

import com.ashehata.orange_task.BuildConfig.API_KEY_VALUE
import com.ashehata.orange_task.common.data.retrofit.ApiPaths
import com.ashehata.orange_task.common.data.retrofit.Query.Companion.API_KEY
import com.ashehata.orange_task.common.data.retrofit.Query.Companion.DATE
import com.ashehata.orange_task.common.data.retrofit.Query.Companion.KEYWORD
import com.ashehata.orange_task.common.data.retrofit.Query.Companion.PAGE
import com.ashehata.orange_task.common.data.retrofit.Query.Companion.PER_PAGE
import com.ashehata.orange_task.common.data.retrofit.Query.Companion.SEARCH_IN
import com.ashehata.orange_task.common.data.retrofit.Query.Companion.SORTED_BY
import com.ashehata.orange_task.common.data.retrofit.Query.Companion.TITLE
import com.ashehata.orange_task.modules.news.data.retrofit.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsService {

    @GET(ApiPaths.ALL_NEWS)
    suspend fun getNews(
        @Query(PAGE) page: Int,
        @Query(PER_PAGE) perPage: Int,
        @Query(KEYWORD) keyword: String,
        @Query(SEARCH_IN) searchIn: String = TITLE,
        @Query(SORTED_BY) sortBy: String = DATE,
        @Query(API_KEY) apiKey: String = API_KEY_VALUE,
    ): NewsResponse
}