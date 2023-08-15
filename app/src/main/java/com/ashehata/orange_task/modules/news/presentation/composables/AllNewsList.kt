package com.ashehata.orange_task.modules.news.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.ashehata.orange_task.common.presentation.compose.EmptyListPlaceholder
import com.ashehata.orange_task.common.presentation.compose.LoadingView
import com.ashehata.orange_task.common.presentation.compose.NetworkErrorView
import com.ashehata.orange_task.modules.news.presentation.model.NewsUIModel

@Composable
fun AllNewsList(
    listState: LazyListState,
    allNews: LazyPagingItems<NewsUIModel>?,
    onArticleClicked: (NewsUIModel) -> Unit,
) {

    val displayEmptyPlaceholder = remember {
        derivedStateOf {
            allNews?.let {
                allNews.loadState.append.endOfPaginationReached && allNews.itemCount < 1
            } ?: true
        }
    }
    allNews?.let {

        if (displayEmptyPlaceholder.value)
            EmptyListPlaceholder(modifier = Modifier.fillMaxSize())

        // First loading state for first page
        else if (allNews.loadState.refresh is LoadState.Loading) {
            LoadingView()

            // Display error if not load first page
        } else if (allNews.loadState.refresh is LoadState.Error) {
            NetworkErrorView { allNews.retry() }

        } else
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                state = listState,

                ) {

                // Display news list
                items(
                    count = allNews.itemCount,
                    key = allNews.itemKey(),
                    contentType = allNews.itemContentType()
                ) { index ->
                    val item = allNews[index]
                    NewsItem(item, onArticleClicked)
                }

                // Display loading indicator at the end of the page
                if (allNews.loadState.append is LoadState.Loading) {
                    item {
                        LoadingView()
                    }
                }

                // Display error message at the end of the page
                if (allNews.loadState.append is LoadState.Error) {
                    // TODO errorMessage can be used later..
                    val errorMessage =
                        (allNews.loadState.append as LoadState.Error).error.localizedMessage
                    item {
                        NetworkErrorView {
                            allNews.retry()
                        }
                    }
                }

            }
    }

}