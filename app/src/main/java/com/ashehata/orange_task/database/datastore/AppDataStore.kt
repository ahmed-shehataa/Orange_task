package com.ashehata.orange_task.database.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ashehata.orange_task.database.models.AppLocal
import com.ashehata.orange_task.database.models.AppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppDataStore @Inject constructor(private val context: Context) {

    /** why companion object here?
     * To prevent creating multi instance from same [preferencesDataStore]
     */
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "MY_PREF")
    }

    private val appLocal = stringPreferencesKey("app_local")
    private val appTheme = stringPreferencesKey("app_theme")


    suspend fun setTheme(theme: AppTheme) {
        context.dataStore.edit { settings ->
            settings[appTheme] = theme.toStringTheme()
        }
    }

    suspend fun setLocal(local: AppLocal) {
        context.dataStore.edit { settings ->
            settings[appLocal] = local.toStringLocal()
        }
    }

    fun getThemeFlow(): Flow<AppTheme> {
        return context.dataStore.data.map { preferences ->
            preferences[appTheme]?.toAppTheme() ?: AppTheme.SYSTEM
        }
    }

    fun getLocalFlow(): Flow<AppLocal> {
        return context.dataStore.data.map { preferences ->
            preferences[appLocal]?.toAppLocal() ?: AppLocal.EN
        }
    }


    private fun String.toAppLocal(): AppLocal {
        return when (this) {
            AppLocal.EN.name -> AppLocal.EN
            AppLocal.AR.name -> AppLocal.AR
            else -> AppLocal.EN
        }
    }

    private fun AppLocal.toStringLocal(): String {
        return this.name
    }

    private fun String.toAppTheme(): AppTheme {
        return when (this) {
            AppTheme.SYSTEM.name -> AppTheme.SYSTEM
            AppTheme.LIGHT.name -> AppTheme.LIGHT
            AppTheme.DARK.name -> AppTheme.DARK
            else -> AppTheme.SYSTEM
        }
    }

    private fun AppTheme.toStringTheme(): String {
        return this.name
    }
}