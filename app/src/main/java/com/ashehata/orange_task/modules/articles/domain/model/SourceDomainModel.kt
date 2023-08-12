package com.ashehata.orange_task.modules.articles.domain.model

import androidx.annotation.Keep
import com.squareup.moshi.Json


@Keep
data class SourceDomainModel(
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?
)