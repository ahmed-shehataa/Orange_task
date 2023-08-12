package com.ashehata.orange_task.modules.news.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SourceUIModel(
    val id: String?,
    val name: String?
) : Parcelable