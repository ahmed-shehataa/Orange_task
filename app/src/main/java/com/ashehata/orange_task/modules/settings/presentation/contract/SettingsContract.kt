package com.ashehata.orange_task.modules.settings.presentation.contract

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.ashehata.orange_task.base.BaseEvent
import com.ashehata.orange_task.base.BaseState
import com.ashehata.orange_task.base.BaseViewState
import com.ashehata.orange_task.database.models.AppLocal
import com.ashehata.orange_task.database.models.AppTheme
import kotlinx.coroutines.flow.Flow

sealed class SettingsEvent : BaseEvent {
    data class ChangeTheme(val appTheme: AppTheme) : SettingsEvent()
    data class ChangeLocal(val appLocal: AppLocal) : SettingsEvent()
}

sealed class SettingsState : BaseState {

}

data class SettingsViewState(
    override val isNetworkError: MutableState<Boolean> = mutableStateOf(false),
    override val isRefreshing: MutableState<Boolean> = mutableStateOf(false),
    override val isLoading: MutableState<Boolean> = mutableStateOf(false),
    val appTheme: MutableState<AppTheme> = mutableStateOf(AppTheme.SYSTEM),
) : BaseViewState
