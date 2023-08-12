package com.ashehata.orange_task.modules.settings.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashehata.orange_task.R
import com.ashehata.orange_task.database.models.AppTheme

@Composable
fun SettingsScreenContent(
    onBackClicked: () -> Unit,
    onLanguageClicked: () -> Unit,
    onThemeClicked: () -> Unit,
    languageExpandedState: MutableState<Boolean>,
    themeExpandedState: MutableState<Boolean>,
    appTheme: AppTheme,
    onChangeTheme: (AppTheme) -> Unit,
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
                    Box(Modifier.height(50.dp)) {

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
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onChangeTheme(it)
                                    }
                                    .padding(horizontal = 40.dp, vertical = 12.dp),
                                verticalAlignment = CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {

                                RadioButton(
                                    selected = it == appTheme,
                                    onClick = null,
                                )

                                Text(
                                    text = it.name,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontSize = 16.sp
                                )


                            }
                        }
                    }

                }
            )
        }

    }
}