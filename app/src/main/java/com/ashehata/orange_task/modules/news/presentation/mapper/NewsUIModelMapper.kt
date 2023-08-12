package com.ashehata.orange_task.modules.news.presentation.mapper

import com.ashehata.orange_task.modules.news.domain.model.NewsDomainModel
import com.ashehata.orange_task.modules.news.domain.model.SourceDomainModel
import com.ashehata.orange_task.modules.news.presentation.model.NewsUIModel
import com.ashehata.orange_task.modules.news.presentation.model.SourceUIModel


fun NewsDomainModel.toUIModel() = NewsUIModel(
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