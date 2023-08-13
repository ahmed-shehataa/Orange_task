package com.ashehata.orange_task.modules.settings.presentation.viewmodel

import com.ashehata.orange_task.base.BaseViewModel
import com.ashehata.orange_task.modules.settings.domain.usecase.GetAppLocalFlowUseCase
import com.ashehata.orange_task.modules.settings.domain.usecase.GetAppThemeFlowUseCase
import com.ashehata.orange_task.modules.settings.domain.usecase.SetAppLocalUseCase
import com.ashehata.orange_task.modules.settings.domain.usecase.SetAppThemeUseCase
import com.ashehata.orange_task.modules.settings.presentation.contract.SettingsEvent
import com.ashehata.orange_task.modules.settings.presentation.contract.SettingsState
import com.ashehata.orange_task.modules.settings.presentation.contract.SettingsViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getAppThemeFlowUseCase: GetAppThemeFlowUseCase,
    private val getAppLocalFlowUseCase: GetAppLocalFlowUseCase,
    private val setAppThemeUseCase: SetAppThemeUseCase,
    private val setAppLocalUseCase: SetAppLocalUseCase
) : BaseViewModel<SettingsEvent, SettingsViewState, SettingsState>() {


    init {
        launchCoroutine {
            getAppThemeFlowUseCase.execute().collectLatest {
                viewStates?.appTheme?.value = it
            }
        }

        launchCoroutine {
            getAppLocalFlowUseCase.execute().collectLatest {
                viewStates?.appLocal?.value = it
            }
        }
    }

    override fun handleEvents(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.ChangeLocal -> {
                launchCoroutine {
                    setAppLocalUseCase.execute(event.appLocal)
                }
            }

            is SettingsEvent.ChangeTheme -> {
                launchCoroutine {
                    setAppThemeUseCase.execute(event.appTheme)
                }
            }

            SettingsEvent.OnLanguageClicked -> {
                viewStates?.isLanguageExpanded?.value =
                    viewStates?.isLanguageExpanded?.value?.not() ?: false
            }

            SettingsEvent.OnThemeClicked -> {
                viewStates?.isThemeExpanded?.value =
                    viewStates?.isThemeExpanded?.value?.not() ?: false
            }
        }
    }

    override fun createInitialViewState(): SettingsViewState {
        return SettingsViewState()
    }
}