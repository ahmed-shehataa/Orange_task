package com.ashehata.orange_task.modules.articles.domain.model

import com.squareup.moshi.Json


data class ArticleDomainModel(
    @Json(name = "author")
    val author: String?,
    @Json(name = "content")
    val content: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "publishedAt")
    val publishedAt: String?,
    @Json(name = "source")
    val source: SourceDomainModel?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "url")
    val url: String?,
    @Json(name = "urlToImage")
    val urlToImage: String?
)