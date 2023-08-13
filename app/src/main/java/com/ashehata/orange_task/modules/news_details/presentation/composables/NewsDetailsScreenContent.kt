package com.ashehata.orange_task.modules.news_details.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ashehata.orange_task.R
import com.ashehata.orange_task.modules.news.presentation.model.NewsUIModel
import com.ashehata.orange_task.util.DoIf
import com.ashehata.orange_task.util.doIf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailsScreenContent(
    news: NewsUIModel,
    onOpenSourceClicked: (String) -> Unit,
    onBackClicked: () -> Unit,
) {

    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val listState = rememberLazyListState()
    val isSourceButtonVisible = remember {
        derivedStateOf {
            (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == listState.layoutInfo.totalItemsCount - 1).not()
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        news.title?.DoIf {
                            Text(
                                it,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 18.sp
                            )
                        }

                        news.formattedDate().DoIf {
                            Text(
                                it,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = 16.sp
                            )
                        }
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClicked() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "ArrowBack"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
        content = { innerPadding ->
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    contentPadding = innerPadding,
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    state = listState
                ) {

                    item {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(150.dp),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(news.urlToImage)
                                .crossfade(true)
                                .build(),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                        )
                    }

                    news.author?.doIf {
                        item {
                            NewsDetailsItem(title = R.string.author, description = it)
                        }
                    }

                    news.description?.doIf {
                        item {
                            NewsDetailsItem(title = R.string.description, description = it)
                        }
                    }

                    news.content?.doIf {
                        item {
                            NewsDetailsItem(title = R.string.content, description = it)
                        }
                    }

                    news.source?.name?.doIf {
                        item {
                            NewsDetailsItem(
                                title = R.string.source,
                                description = it,
                                hasDivider = false,
                                extraBody = {
                                    SourceButton(Modifier, news.url, onOpenSourceClicked)
                                }
                            )
                        }
                    }
                }

                AnimatedVisibility(
                    visible = isSourceButtonVisible.value,
                    enter = scaleIn(),
                    exit = scaleOut(),
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                ) {
                    SourceButton(
                        Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 20.dp),
                        news.url,
                        onOpenSourceClicked
                    )
                }


            }
        }
    )

}