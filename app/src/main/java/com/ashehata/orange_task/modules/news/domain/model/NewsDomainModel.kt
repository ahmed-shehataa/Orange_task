package com.ashehata.orange_task.modules.news.domain.model


data class NewsDomainModel(
    val url: String?,
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val source: SourceDomainModel? = null,
    val title: String? = null,
    val urlToImage: String? = null
)