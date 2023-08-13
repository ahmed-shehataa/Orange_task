package com.ashehata.orange_task.modules.settings.domain.usecase

import com.ashehata.orange_task.database.models.AppLocal
import com.ashehata.orange_task.database.models.AppTheme
import com.ashehata.orange_task.modules.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppLocalFlowUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {

    suspend fun execute(): Flow<AppLocal> {
        return settingsRepository.getLocal()
    }
}