package com.ashehata.orange_task.modules.settings.domain.usecase

import com.ashehata.orange_task.database.models.AppTheme
import com.ashehata.orange_task.modules.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SetAppThemeUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    suspend fun execute(appTheme: AppTheme) {
        settingsRepository.setTheme(appTheme)
    }
}