package com.ashehata.orange_task.modules.settings.data.local

import com.ashehata.orange_task.database.models.AppLocal
import com.ashehata.orange_task.database.models.AppTheme
import kotlinx.coroutines.flow.Flow

interface SettingsLocalDataSource {

    suspend fun getTheme(): Flow<AppTheme>

    suspend fun setTheme(appTheme: AppTheme)

    suspend fun getLocal(): Flow<AppLocal>

    suspend fun setLocal(appLocal: AppLocal)
}