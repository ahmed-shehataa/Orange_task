package com.ashehata.orange_task.modules.news.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.ashehata.orange_task.R
import com.ashehata.orange_task.common.presentation.compose.LoadingView
import com.ashehata.orange_task.common.presentation.compose.NetworkErrorView
import com.ashehata.orange_task.modules.news.presentation.model.NewsUIModel
import kotlinx.coroutines.launch


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

            }
        ) { _ ->

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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NewsFabIcon(isScrollUpButtonVisible: Boolean, allListState: LazyListState) {
    val scope = rememberCoroutineScope()

    AnimatedVisibility(
        visible = isScrollUpButtonVisible,
        enter = scaleIn(),
        exit = scaleOut()
    ) {
        FloatingActionButton(
            onClick = {
                scope.launch {
                    allListState.animateScrollToItem(0)
                }
            },
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowUp,
                contentDescription = "ArrowForward",
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopAppBar(onSettingsClicked: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(id = R.string.news),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(),
        actions = {

            IconButton(onClick = {
                onSettingsClicked()
            }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings",
                )
            }
        },
    )
}
