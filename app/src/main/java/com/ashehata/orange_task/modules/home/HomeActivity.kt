package com.ashehata.orange_task.modules.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.ashehata.orange_task.modules.news.presentation.composables.NewsScreen
import com.ashehata.orange_task.modules.news.presentation.viewmodel.NewsViewModel
import com.ashehata.orange_task.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            AppTheme {
                setContent {
                    val viewModel: NewsViewModel by viewModels()
                    NewsScreen(viewModel = viewModel, navController = navController)
                }
            }
        }

    }
}
