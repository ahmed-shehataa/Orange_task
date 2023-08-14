package com.ashehata.orange_task.modules.news.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ashehata.orange_task.common.presentation.compose.LoadingView
import com.ashehata.orange_task.common.presentation.compose.NetworkErrorView
import com.ashehata.orange_task.modules.news.presentation.model.NewsUIModel


@OptIn(
    ExperimentalMaterialApi::class,
)
@Composable
fun NewsScreenContent(
    allNews: List<NewsUIModel?>,
    isLoading: Boolean,
    isRefreshing: Boolean,
    isNetworkError: Boolean,
    onArticleClicked: (NewsUIModel) -> Unit,
    onRefresh: () -> Unit,
    onSettingsClicked: () -> Unit,
    searchText: MutableState<String>,
    onSearch: (String) -> Unit,
) {

    val refreshState = rememberPullRefreshState(isRefreshing, onRefresh)
    val allListState = rememberLazyListState()
    val isScrollUpButtonVisible = remember {
        derivedStateOf {
            allListState.firstVisibleItemIndex > 0
        }
    }

    Column(Modifier.pullRefresh(refreshState)) {

        Scaffold(
            topBar = {
                NewsTopAppBar(onSettingsClicked)
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                NewsFabIcon(isScrollUpButtonVisible.value, allListState)

            },
            backgroundColor = MaterialTheme.colorScheme.onPrimary
        ) { _ ->

            Column {

                NewsSearchBar(searchText, onSearch)

                if (isNetworkError) {
                    NetworkErrorView(onRefresh)

                } else if (isLoading) {
                    LoadingView()

                } else {

                    Box(Modifier.pullRefresh(refreshState)) {

                        AllNewsList(
                            allListState, allNews, onArticleClicked
                        )

                        PullRefreshIndicator(
                            isRefreshing,
                            refreshState,
                            Modifier.align(Alignment.TopCenter)
                        )

                    }

                }
            }

        }
    }
}