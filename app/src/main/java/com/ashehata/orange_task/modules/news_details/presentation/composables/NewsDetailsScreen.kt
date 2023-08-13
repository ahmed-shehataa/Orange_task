package com.ashehata.orange_task.modules.news_details.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.ashehata.orange_task.modules.news.presentation.model.NewsUIModel
import com.ashehata.orange_task.util.openLink

@Composable
fun NewsDetailsScreen(newsUIModel: NewsUIModel, navController: NavHostController) {

    val context = LocalContext.current

    NewsDetailsScreenContent(
        news = newsUIModel,
        onBackClicked = { navController.navigateUp() },
        onOpenSourceClicked = {
            context.openLink(it)
        }
    )

}