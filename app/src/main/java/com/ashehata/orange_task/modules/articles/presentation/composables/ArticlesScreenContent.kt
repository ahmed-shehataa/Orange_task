package com.ashehata.orange_task.modules.articles.presentation.composables

import androidx.compose.animation.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ashehata.orange_task.common.presentation.compose.LoadingView
import com.ashehata.orange_task.common.presentation.compose.NetworkErrorView
import com.ashehata.orange_task.modules.articles.presentation.model.ArticleUIModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ArticlesScreenContent(
    isLoading: Boolean,
    isRefreshing: Boolean,
    isNetworkError: Boolean,
    onArticleClicked: (ArticleUIModel) -> Unit,
    onRefresh: () -> Unit,
    allArticles: List<ArticleUIModel?>,
) {

    val refreshState = rememberPullRefreshState(isRefreshing, onRefresh)
    val allListState = rememberLazyListState()

    Column(Modifier.pullRefresh(refreshState)) {

        TopAppBar(
            elevation = 4.dp,
            title = {
                //Text(screenTitle(), maxLines = 1, overflow = TextOverflow.Ellipsis)
            },
            backgroundColor = Color.White,
            actions = {

                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Favorite",
                        //tint = colorAnimated.value
                    )
                }

            })

        if (isNetworkError) {
            NetworkErrorView(onRefresh)

        } else if (isLoading) {
            LoadingView()

        } else {

            Box(Modifier.pullRefresh(refreshState)) {

                AllArticlesList(
                    allListState, allArticles, onArticleClicked
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