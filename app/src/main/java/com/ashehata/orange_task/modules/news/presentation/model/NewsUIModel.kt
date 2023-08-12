package com.ashehata.orange_task.modules.news.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsUIModel(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: SourceUIModel?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : Parcelable