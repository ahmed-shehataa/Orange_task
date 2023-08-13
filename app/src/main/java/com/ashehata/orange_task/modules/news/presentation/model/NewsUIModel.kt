package com.ashehata.orange_task.modules.news.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

@Parcelize
data class NewsUIModel(
    val author: String? = null,
    val content: String? = null,
    val description: String? = null,
    val publishedAt: String? = null,
    val source: SourceUIModel? = null,
    val title: String? = null,
    val url: String? = null,
    val urlToImage: String? = null
) : Parcelable {
    fun formattedDate(): String? {
        return try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            val date = dateFormat.parse(publishedAt ?: "")
            SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(date)
        } catch (e: Exception) {
            null
        }
    }
}