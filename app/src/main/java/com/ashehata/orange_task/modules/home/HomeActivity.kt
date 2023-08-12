package com.ashehata.orange_task.modules.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.ashehata.orange_task.modules.articles.presentation.composables.ArticlesScreen
import com.ashehata.orange_task.modules.articles.presentation.viewmodel.ArticlesViewModel
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
                    val viewModel: ArticlesViewModel by viewModels()
                    ArticlesScreen(viewModel = viewModel, navController = navController)

                }
            }
        }

    }
}
