package com.ashehata.orange_task.modules.home

sealed class HomeDestinations(val route: String) {
    object NewsScreen : HomeDestinations("news")
    object SettingsScreen : HomeDestinations("settings")
    data class NewsDetailsScreen(val key: String = "news") : HomeDestinations("news_details/{$key}") {
        fun open(newsJsonString: String) = "news_details/$newsJsonString"
    }
}
