package com.ashehata.orange_task.modules.settings.domain.repository

import com.ashehata.orange_task.database.models.AppLocal
import com.ashehata.orange_task.database.models.AppTheme
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    suspend fun getTheme(): Flow<AppTheme>

    suspend fun setTheme(appTheme: AppTheme)

    suspend fun getLocal(): AppLocal

    suspend fun setLocal(appLocal: AppLocal)
}