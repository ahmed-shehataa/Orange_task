package com.ashehata.orange_task.modules.settings.di

import com.ashehata.orange_task.modules.settings.data.local.SettingsLocalDataSource
import com.ashehata.orange_task.modules.settings.data.local.SettingsLocalDataSourceImpl
import com.ashehata.orange_task.modules.settings.data.repository.SettingsRepositoryImpl
import com.ashehata.orange_task.modules.settings.domain.repository.SettingsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class SettingsModule {

    @ViewModelScoped
    @Binds
    abstract fun bindSettingsLocalDataSource(settingsLocalDataSourceImpl: SettingsLocalDataSourceImpl): SettingsLocalDataSource


    @ViewModelScoped
    @Binds
    abstract fun bindSettingsRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository

}