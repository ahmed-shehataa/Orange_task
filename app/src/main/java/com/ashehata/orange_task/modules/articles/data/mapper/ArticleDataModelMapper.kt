package com.ashehata.orange_task.modules.articles.data.mapper

import com.ashehata.orange_task.modules.articles.data.model.ArticleDataModel
import com.ashehata.orange_task.modules.articles.data.model.SourceDataModel
import com.ashehata.orange_task.modules.articles.domain.model.ArticleDomainModel
import com.ashehata.orange_task.modules.articles.domain.model.SourceDomainModel

fun ArticleDataModel.toDomainModel() = ArticleDomainModel(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = source?.toDomainModel(),
    title = title,
    url = url,
    urlToImage = urlToImage
)

fun SourceDataModel.toDomainModel() = SourceDomainModel(
    id, name
)
