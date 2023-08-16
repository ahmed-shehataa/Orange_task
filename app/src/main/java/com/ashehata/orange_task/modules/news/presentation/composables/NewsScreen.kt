package com.ashehata.orange_task.modules.news.presentation.composables

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.ashehata.orange_task.common.presentation.GeneralObservers
import com.ashehata.orange_task.modules.home.HomeDestinations
import com.ashehata.orange_task.modules.news.presentation.contract.NewsEvent
import com.ashehata.orange_task.modules.news.presentation.contract.NewsState
import com.ashehata.orange_task.modules.news.presentation.contract.NewsViewState
import com.ashehata.orange_task.modules.news.presentation.model.NewsUIModel
import com.ashehata.orange_task.modules.news.presentation.viewmodel.NewsViewModel
import com.ashehata.orange_task.util.extensions.navigate
import kotlinx.coroutines.flow.Flow

@Composable
fun NewsScreen(
    viewModel: NewsViewModel,
    navController: NavController,
) {

    val context = LocalContext.current

    val viewStates = remember {
        viewModel.viewStates ?: NewsViewState()
    }

    val allNews = (viewStates.newsFlow as Flow<PagingData<NewsUIModel>>).collectAsLazyPagingItems()


    val searchText = remember {
        viewStates.searchText
    }

    val isRefreshing = remember {
        viewStates.isRefreshing
    }

    val onArticleClicked: (NewsUIModel) -> Unit = remember {
        {
            viewModel.setEvent(NewsEvent.OnArticleClicked(it))
        }
    }

    val onSearch: (String) -> Unit = remember {
        {
            viewModel.setEvent(NewsEvent.OnSearch(it))
        }
    }

    val onSettingsClicked = remember {
        {
            viewModel.setEvent(NewsEvent.OnSettingClicked)
        }
    }

    val onRefresh = remember {
        {
            viewModel.setEvent(NewsEvent.RefreshScreen)
        }
    }

    NewsScreenContent(
        isRefreshing = isRefreshing.value,
        allNews = allNews,
        onArticleClicked = onArticleClicked,
        onRefresh = onRefresh,
        onSettingsClicked = onSettingsClicked,
        searchText = searchText,
        onSearch = onSearch,
    )

    GeneralObservers<NewsState, NewsViewModel>(viewModel = viewModel) {
        when (it) {
            is NewsState.OpenArticleDetailsScreen -> {
                val destination = HomeDestinations.NewsDetailsScreen()
                navController.navigate(route = destination.route, args = Bundle().apply {
                    putParcelable(destination.key, it.news)
                })
            }

            NewsState.OpenSettingScreen -> {
                navController.navigate(HomeDestinations.SettingsScreen.route)
            }
        }
    }

}