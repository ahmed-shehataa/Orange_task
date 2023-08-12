package com.ashehata.orange_task.modules.settings.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.ashehata.orange_task.database.models.AppLocal
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

    val languageExpandedState = remember {
        viewStates.isLanguageExpanded
    }

    val themeExpandedState = remember {
        viewStates.isThemeExpanded
    }

    val appTheme = remember {
        viewStates.appTheme
    }

    val onChangeTheme: (AppTheme) -> Unit = remember {
        {
            viewModel.setEvent(SettingsEvent.ChangeTheme(it))

        }
    }

    val onChangeLocal: (AppLocal) -> Unit = remember {
        {
            viewModel.setEvent(SettingsEvent.ChangeLocal(it))

        }
    }

    val onLanguageClicked = remember {
        {
            viewModel.setEvent(SettingsEvent.OnLanguageClicked)

        }
    }

    val onThemeClicked = remember {
        {
            viewModel.setEvent(SettingsEvent.OnThemeClicked)

        }
    }

    SettingsScreenContent(
        onBackClicked = {
            navController.navigateUp()
        },
        onLanguageClicked = onLanguageClicked,
        onThemeClicked = onThemeClicked,
        languageExpandedState = languageExpandedState,
        themeExpandedState = themeExpandedState,
        appTheme = appTheme.value,
        onChangeTheme = onChangeTheme,
    )

    /* GeneralObservers<SettingsState, SettingsViewModel>(viewModel = viewModel) {
         when (it) {
             is SettingsState.OpenArticleDetailsScreen -> {

             }

             SettingsState.OpenSettingScreen -> {

             }
         }
     }*/

}