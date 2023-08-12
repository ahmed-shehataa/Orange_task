package com.ashehata.orange_task.modules.articles.presentation.mapper

import com.ashehata.orange_task.modules.articles.domain.model.ArticleDomainModel
import com.ashehata.orange_task.modules.articles.domain.model.SourceDomainModel
import com.ashehata.orange_task.modules.articles.presentation.model.ArticleUIModel
import com.ashehata.orange_task.modules.articles.presentation.model.SourceUIModel


fun ArticleDomainModel.toUIModel() = ArticleUIModel(
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = source?.toUIModel(),
    title = title,
    url = url,
    urlToImage = urlToImage
)

fun SourceDomainModel.toUIModel() = SourceUIModel(
    id, name
)