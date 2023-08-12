package com.ashehata.orange_task.modules.settings.data.repository

import com.ashehata.orange_task.database.models.AppLocal
import com.ashehata.orange_task.database.models.AppTheme
import com.ashehata.orange_task.modules.settings.data.local.SettingsLocalDataSource
import com.ashehata.orange_task.modules.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val local: SettingsLocalDataSource
) : SettingsRepository {
    override suspend fun getTheme(): Flow<AppTheme> {
        return local.getTheme()
    }

    override suspend fun setTheme(appTheme: AppTheme) {
        local.setTheme(appTheme)
    }

    override suspend fun getLocal(): AppLocal {
        return local.getLocal()
    }

    override suspend fun setLocal(appLocal: AppLocal) {
        local.setLocal(appLocal)
    }
}