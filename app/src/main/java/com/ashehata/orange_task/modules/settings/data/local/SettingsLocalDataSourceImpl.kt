package com.ashehata.orange_task.modules.settings.data.local

import com.ashehata.orange_task.database.datastore.AppDataStore
import com.ashehata.orange_task.database.models.AppLocal
import com.ashehata.orange_task.database.models.AppTheme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsLocalDataSourceImpl @Inject constructor(
    private val appDataStore: AppDataStore
) : SettingsLocalDataSource {
    override suspend fun getTheme(): Flow<AppTheme> {
        return appDataStore.getThemeFlow()
    }

    override suspend fun setTheme(appTheme: AppTheme) {
        appDataStore.setTheme(appTheme)
    }

    override suspend fun getLocal(): Flow<AppLocal> {
        return appDataStore.getLocalFlow()
    }

    override suspend fun setLocal(appLocal: AppLocal) {
        appDataStore.setLocal(appLocal)
    }
}