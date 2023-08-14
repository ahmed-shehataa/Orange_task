package com.ashehata.orange_task.modules.news.presentation.contract

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.ashehata.orange_task.base.BaseEvent
import com.ashehata.orange_task.base.BaseState
import com.ashehata.orange_task.base.BaseViewState
import com.ashehata.orange_task.modules.news.presentation.model.NewsUIModel

sealed class NewsEvent : BaseEvent {
    data class OnArticleClicked(val news: NewsUIModel) : NewsEvent()
    data class OnSearch(val keyword: String) : NewsEvent()
    object OnSettingClicked : NewsEvent()
    object RefreshScreen : NewsEvent()
}

sealed class NewsState : BaseState {
    data class OpenArticleDetailsScreen(val news: NewsUIModel) : NewsState()
    object OpenSettingScreen : NewsState()

}

data class NewsViewState(
    override val isNetworkError: MutableState<Boolean> = mutableStateOf(false),
    override val isRefreshing: MutableState<Boolean> = mutableStateOf(false),
    override val isLoading: MutableState<Boolean> = mutableStateOf(false),
    val searchText: MutableState<String> = mutableStateOf(""),
    val allNews: MutableList<NewsUIModel?> = SnapshotStateList(),
    ) : BaseViewState
