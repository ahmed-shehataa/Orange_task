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
    val author: String?,
    @Json(name = "content")
    val content: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "publishedAt")
    val publishedAt: String?,
    @Json(name = "source")
    val source: SourceDataModel?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "urlToImage")
    val urlToImage: String?
)