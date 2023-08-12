package com.ashehata.orange_task.modules.news.data.mapper

import com.ashehata.orange_task.modules.news.data.model.NewsDataModel
import com.ashehata.orange_task.modules.news.data.model.SourceDataModel
import com.ashehata.orange_task.modules.news.domain.model.NewsDomainModel
import com.ashehata.orange_task.modules.news.domain.model.SourceDomainModel

fun NewsDataModel.toDomainModel() = NewsDomainModel(
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
