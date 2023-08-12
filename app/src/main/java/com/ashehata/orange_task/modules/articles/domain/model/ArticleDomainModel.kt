package com.ashehata.orange_task.modules.articles.domain.model

import com.squareup.moshi.Json


data class ArticleDomainModel(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: SourceDomainModel?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)