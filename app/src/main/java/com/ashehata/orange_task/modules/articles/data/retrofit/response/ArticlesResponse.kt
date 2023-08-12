package com.ashehata.orange_task.modules.articles.data.retrofit.response

import androidx.annotation.Keep
import com.ashehata.orange_task.modules.articles.data.model.ArticleDataModel
import com.squareup.moshi.Json



@Keep
data class ArticlesResponse(
    @Json(name = "articles")
    val articles: List<ArticleDataModel>?,
    @Json(name = "status")
    val status: String?,
    @Json(name = "totalResults")
    val totalResults: Int?
)