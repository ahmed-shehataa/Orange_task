package com.ashehata.orange_task.modules.news.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.ashehata.orange_task.modules.news.presentation.model.NewsUIModel

@Composable
fun AllNewsList(
    listState: LazyListState,
    allRecipes: List<NewsUIModel?>,
    onArticleClicked: (NewsUIModel) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("allList"),
        contentPadding = PaddingValues(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        state = listState
    ) {

        items(allRecipes) {
            NewsItem(
                it,
                onArticleClicked
            )
        }

    }
}