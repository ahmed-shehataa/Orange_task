package com.ashehata.orange_task.modules.home

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ashehata.orange_task.modules.news.presentation.composables.NewsScreen
import com.ashehata.orange_task.modules.news.presentation.model.NewsUIModel
import com.ashehata.orange_task.modules.news.presentation.viewmodel.NewsViewModel
import com.ashehata.orange_task.modules.news_details.presentation.composables.NewsDetailsScreen
import com.ashehata.orange_task.modules.settings.presentation.composables.SettingsScreen
import com.ashehata.orange_task.modules.settings.presentation.contract.SettingsViewState
import com.ashehata.orange_task.modules.settings.presentation.viewmodel.SettingsViewModel
import com.ashehata.orange_task.theme.AppTheme
import com.ashehata.orange_task.util.parcelable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewStates = remember { settingsViewModel.viewStates ?: SettingsViewState() }
            val appTheme = remember { viewStates.appTheme }
            val appLocal = remember { viewStates.appLocal }

            AppTheme(appTheme.value, appLocal.value) {
                NavHost(navController = navController, startDestination = HomeDestinations.NewsScreen.route) {

                    composable(HomeDestinations.NewsScreen.route) {
                        val viewModel: NewsViewModel by viewModels()
                        NewsScreen(viewModel = viewModel, navController = navController)
                    }

                    composable(HomeDestinations.SettingsScreen.route) {
                        SettingsScreen(
                            viewModel = settingsViewModel,
                            navController = navController
                        )
                    }

                    composable(HomeDestinations.NewsDetailsScreen().route) {
                        val news = it.arguments?.parcelable<NewsUIModel>(HomeDestinations.NewsDetailsScreen().key)
                        if (news != null) {
                            NewsDetailsScreen(newsUIModel = news, navController = navController)
                        }
                    }
                }
            }
        }

    }
}