package com.ashehata.orange_task.modules.settings.domain.usecase

import com.ashehata.orange_task.database.models.AppLocal
import com.ashehata.orange_task.modules.settings.domain.repository.SettingsRepository
import javax.inject.Inject

class SetAppLocalUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    suspend fun execute(appLocal: AppLocal) {
        settingsRepository.setLocal(appLocal)
    }
}