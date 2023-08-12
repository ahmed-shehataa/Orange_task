package com.ashehata.orange_task.modules.settings.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.ashehata.orange_task.database.models.AppTheme
import com.ashehata.orange_task.modules.settings.presentation.contract.SettingsEvent
import com.ashehata.orange_task.modules.settings.presentation.contract.SettingsViewState
import com.ashehata.orange_task.modules.settings.presentation.viewmodel.SettingsViewModel


@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    navController: NavController,
) {

    val context = LocalContext.current

    val viewStates = remember {
        viewModel.viewStates ?: SettingsViewState()
    }

    Column {
        Button(onClick = {
            viewModel.setEvent(SettingsEvent.ChangeTheme(AppTheme.DARK))
        }) {
            Text(text = "Dark")
        }

        Button(onClick = {
            viewModel.setEvent(SettingsEvent.ChangeTheme(AppTheme.LIGHT))
        }) {
            Text(text = "Light")
        }

        Button(onClick = {
            viewModel.setEvent(SettingsEvent.ChangeTheme(AppTheme.SYSTEM))
        }) {
            Text(text = "SYSTEM")
        }
    }


    /*SettingsScreenContent(

    )

    GeneralObservers<SettingsState, SettingsViewModel>(viewModel = viewModel) {
        when (it) {
            is SettingsState.OpenArticleDetailsScreen -> {

            }

            SettingsState.OpenSettingScreen -> {

            }
        }
    }*/

}