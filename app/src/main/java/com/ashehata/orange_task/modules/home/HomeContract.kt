package com.ashehata.orange_task.modules.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ashehata.orange_task.base.BaseState
import com.ashehata.orange_task.base.BaseViewState


sealed class HomeState : BaseState {
    object OpenLoginScreen : HomeState()
    object OpenRecipesScreen : HomeState()
}

data class HomeViewState(
    override val isRefreshing: MutableState<Boolean> = mutableStateOf(false),
    override val isNetworkError: MutableState<Boolean> = mutableStateOf(false),
    override val isLoading: MutableState<Boolean> = mutableStateOf(false),
) : BaseViewState