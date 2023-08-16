package com.ashehata.orange_task.modules.settings.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.ashehata.orange_task.R
import com.ashehata.orange_task.common.presentation.compose.AlertDialog
import com.ashehata.orange_task.database.models.AppLocal
import com.ashehata.orange_task.database.models.AppTheme
import com.ashehata.orange_task.modules.home.HomeActivity
import com.ashehata.orange_task.util.extensions.restartActivity

@Composable
fun SettingsScreenContent(
    onBackClicked: () -> Unit,
    onLanguageClicked: () -> Unit,
    onThemeClicked: () -> Unit,
    languageExpandedState: MutableState<Boolean>,
    themeExpandedState: MutableState<Boolean>,
    currentAppTheme: AppTheme?,
    currentAppLocal: AppLocal,
    onChangeTheme: (AppTheme) -> Unit,
    onChangeLocal: (AppLocal) -> Unit,
    localDialogState: MutableState<Boolean>,
) {

    val activity = LocalContext.current as? HomeActivity

    Scaffold(
        topBar = {
            SettingsTopAppBar(onBackClicked)
        },
        backgroundColor = MaterialTheme.colorScheme.onPrimary
    ) { _ ->

        Column(Modifier.fillMaxSize()) {
            SettingsItem(
                titleRes = R.string.language,
                iconRes = R.drawable.ic_local,
                onClicked = onLanguageClicked,
                isExpanded = languageExpandedState,
                expandedList = {
                    Column(Modifier.selectableGroup()) {
                        AppLocal.values().forEach {
                            RadioLocalItem(it, currentAppLocal) {
                                localDialogState.value = true
                            }
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

    AlertDialog(
        state = localDialogState,
        title = R.string.change_local,
        content = R.string.are_you_sure_to_change_local,
        positiveTitleRes = R.string.sure ,
        negativeTitleRes = R.string.cancel,
        positive = {
            val local = when(currentAppLocal) {
                AppLocal.AR -> AppLocal.EN
                AppLocal.EN -> AppLocal.AR
            }
            onChangeLocal(local)
            activity?.restartActivity()
        }
    )
}