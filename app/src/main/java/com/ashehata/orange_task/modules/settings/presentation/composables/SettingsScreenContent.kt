package com.ashehata.orange_task.modules.settings.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.ashehata.orange_task.R
import com.ashehata.orange_task.database.models.AppLocal
import com.ashehata.orange_task.database.models.AppTheme

@Composable
fun SettingsScreenContent(
    onBackClicked: () -> Unit,
    onLanguageClicked: () -> Unit,
    onThemeClicked: () -> Unit,
    languageExpandedState: MutableState<Boolean>,
    themeExpandedState: MutableState<Boolean>,
    currentAppTheme: AppTheme,
    currentAppLocal: AppLocal,
    onChangeTheme: (AppTheme) -> Unit,
    onChangeLocal: (AppLocal) -> Unit,
) {

    Scaffold(
        topBar = {
            SettingsTopAppBar(onBackClicked)
        },
    ) { _ ->

        Column {
            SettingsItem(
                titleRes = R.string.language,
                iconRes = R.drawable.ic_local,
                onClicked = onLanguageClicked,
                isExpanded = languageExpandedState,
                expandedList = {
                    Column(Modifier.selectableGroup()) {
                        AppLocal.values().forEach {
                            RadioLocalItem(it, currentAppLocal, onChangeLocal)
                        }
                    }
                }
            )

            SettingsItem(
                titleRes = R.string.theme,
                iconRes = R.drawable.ic_theme,
                onClicked = onThemeClicked,
                isExpanded = themeExpandedState,
                expandedList = {
                    Column(Modifier.selectableGroup()) {
                        AppTheme.values().forEach {
                            RadioThemeItem(it, currentAppTheme, onChangeTheme)
                        }
                    }
                }
            )
        }

    }
}