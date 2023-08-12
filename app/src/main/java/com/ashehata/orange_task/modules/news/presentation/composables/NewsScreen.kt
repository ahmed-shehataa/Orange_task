package com.ashehata.orange_task.modules.news.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.ashehata.orange_task.common.presentation.GeneralObservers
import com.ashehata.orange_task.modules.news.presentation.contract.NewsEvent
import com.ashehata.orange_task.modules.news.presentation.contract.NewsState
import com.ashehata.orange_task.modules.news.presentation.contract.NewsViewState
import com.ashehata.orange_task.modules.news.presentation.model.NewsUIModel
import com.ashehata.orange_task.modules.news.presentation.viewmodel.NewsViewModel


@Composable
fun NewsScreen(
    viewModel: NewsViewModel,
    navController: NavController,
) {

    val context = LocalContext.current

    val viewStates = remember {
        viewModel.viewStates ?: NewsViewState()
    }

    val allNews = remember {
        viewStates.allNews
    }

    val isLoading = remember {
        viewStates.isLoading
    }

    val searchText = remember {
        viewStates.searchText
    }

    val isRefreshing = remember {
        viewStates.isRefreshing
    }

    val isNetworkError = remember {
        viewStates.isNetworkError
    }

    val onArticleClicked: (NewsUIModel) -> Unit = remember {
        {
            viewModel.setEvent(NewsEvent.OnArticleClicked(it))
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
        isLoading = isLoading.value,
        isRefreshing = isRefreshing.value,
        isNetworkError = isNetworkError.value,
        allNews = allNews,
        onArticleClicked = onArticleClicked,
        onRefresh = onRefresh,
        onSettingsClicked = onSettingsClicked,
        searchText = searchText
    )

    GeneralObservers<NewsState, NewsViewModel>(viewModel = viewModel) {
        when (it) {
            is NewsState.OpenArticleDetailsScreen -> {

            }

            NewsState.OpenSettingScreen -> {
                navController.navigate("settings")
            }
        }
    }

}