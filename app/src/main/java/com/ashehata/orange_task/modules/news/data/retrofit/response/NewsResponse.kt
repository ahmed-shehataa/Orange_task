package com.ashehata.orange_task.modules.news.data.retrofit.response

import androidx.annotation.Keep
import com.ashehata.orange_task.modules.news.data.model.NewsDataModel
import com.squareup.moshi.Json



@Keep
data class NewsResponse(
    @Json(name = "articles")
    val articles: List<NewsDataModel>?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "totalResults")
    val totalResults: Int?
)