package com.ashehata.orange_task.modules.articles.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.ashehata.orange_task.common.presentation.GeneralObservers
import com.ashehata.orange_task.modules.articles.presentation.contract.ArticlesEvent
import com.ashehata.orange_task.modules.articles.presentation.contract.ArticlesState
import com.ashehata.orange_task.modules.articles.presentation.contract.ArticlesViewState
import com.ashehata.orange_task.modules.articles.presentation.model.ArticleUIModel
import com.ashehata.orange_task.modules.articles.presentation.viewmodel.ArticlesViewModel


@Composable
fun ArticlesScreen(
    viewModel: ArticlesViewModel,
    navController: NavController,
) {

    val context = LocalContext.current

    val viewStates = remember {
        viewModel.viewStates ?: ArticlesViewState()
    }

    val allArticles = remember {
        viewStates.allArticles
    }

    val isLoading = remember {
        viewStates.isLoading
    }

    val isRefreshing = remember {
        viewStates.isRefreshing
    }

    val isNetworkError = remember {
        viewStates.isNetworkError
    }

    val onArticleClicked: (ArticleUIModel) -> Unit = remember {
        {
            viewModel.setEvent(ArticlesEvent.OnArticleClicked(it))
        }
    }

    val onSettingsClicked = remember {
        {
            viewModel.setEvent(ArticlesEvent.OnSettingClicked)
        }
    }

    val onRefresh = remember {
        {
            viewModel.setEvent(ArticlesEvent.RefreshScreen)
        }
    }

    ArticlesScreenContent(
        isLoading = isLoading.value,
        isRefreshing = isRefreshing.value,
        isNetworkError = isNetworkError.value,
        allArticles = allArticles,
        onArticleClicked = onArticleClicked,
        onRefresh = onRefresh,
    )

    GeneralObservers<ArticlesState, ArticlesViewModel>(viewModel = viewModel) {
        when (it) {
            is ArticlesState.OpenArticleDetailsScreen -> {

            }

            ArticlesState.OpenSettingScreen -> {

            }
        }
    }

}