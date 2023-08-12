package com.ashehata.orange_task.modules.home

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ashehata.orange_task.database.models.AppTheme
import com.ashehata.orange_task.modules.news.presentation.composables.NewsScreen
import com.ashehata.orange_task.modules.news.presentation.viewmodel.NewsViewModel
import com.ashehata.orange_task.modules.settings.presentation.composables.SettingsScreen
import com.ashehata.orange_task.modules.settings.presentation.contract.SettingsViewState
import com.ashehata.orange_task.modules.settings.presentation.viewmodel.SettingsViewModel
import com.ashehata.orange_task.theme.AppTheme
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
            Log.i("setContent: ", appTheme.value.name)

            AppTheme(appTheme.value) {
                NavHost(navController = navController, startDestination = "news") {
                    composable("news") {
                        val viewModel: NewsViewModel by viewModels()
                        NewsScreen(viewModel = viewModel, navController = navController)
                    }
                    composable("settings") {
                        SettingsScreen(
                            viewModel = settingsViewModel,
                            navController = navController
                        )
                    }
                }
            }
        }

    }
}
