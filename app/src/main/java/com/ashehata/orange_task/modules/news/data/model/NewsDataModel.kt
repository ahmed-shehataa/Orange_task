package com.ashehata.orange_task.modules.news.data.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Keep
@Entity(tableName = "news")
data class NewsDataModel(
    @PrimaryKey
    @Json(name = "url")
    val url: String,
    @Json(name = "author")
    val author: String? = null,
    @Json(name = "content")
    val content: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "publishedAt")
    val publishedAt: String? = null,
    @Json(name = "source")
    val source: SourceDataModel? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "urlToImage")
    val urlToImage: String? = null,
)