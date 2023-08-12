package com.ashehata.orange_task.modules.news.domain.model


data class NewsDomainModel(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: SourceDomainModel?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)