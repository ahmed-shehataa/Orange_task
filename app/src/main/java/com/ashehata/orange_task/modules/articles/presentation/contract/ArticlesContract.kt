package com.ashehata.orange_task.modules.articles.presentation.contract

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.ashehata.orange_task.base.BaseEvent
import com.ashehata.orange_task.base.BaseState
import com.ashehata.orange_task.base.BaseViewState
import com.ashehata.orange_task.modules.articles.presentation.model.ArticleUIModel

sealed class ArticlesEvent : BaseEvent {
    data class OnArticleClicked(val article: ArticleUIModel) : ArticlesEvent()
    object OnSettingClicked : ArticlesEvent()
    object RefreshScreen : ArticlesEvent()
}

sealed class ArticlesState : BaseState {
    data class OpenArticleDetailsScreen(val article: ArticleUIModel) : ArticlesState()
    object OpenSettingScreen : ArticlesState()

}

data class ArticlesViewState(
    override val isNetworkError: MutableState<Boolean> = mutableStateOf(false),
    override val isRefreshing: MutableState<Boolean> = mutableStateOf(false),
    override val isLoading: MutableState<Boolean> = mutableStateOf(false),
    val allArticles: MutableList<ArticleUIModel?> = SnapshotStateList(),
) : BaseViewState
