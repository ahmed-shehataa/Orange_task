package com.ashehata.orange_task.modules.news.data.model

import androidx.annotation.Keep
import com.squareup.moshi.Json


@Keep
data class SourceDataModel(
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?
)