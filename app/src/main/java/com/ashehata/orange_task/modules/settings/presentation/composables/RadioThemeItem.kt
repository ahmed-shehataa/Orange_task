package com.ashehata.orange_task.modules.settings.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashehata.orange_task.R
import com.ashehata.orange_task.database.models.AppTheme

@Composable
fun RadioThemeItem(
    appTheme: AppTheme,
    currentAppTheme: AppTheme?,
    onChangeTheme: (AppTheme) -> Unit
) {


    val displayedName: () -> Int = remember(appTheme) {
        {
            when (appTheme) {
                AppTheme.SYSTEM -> R.string.system
                AppTheme.LIGHT -> R.string.light
                AppTheme.DARK -> R.string.dark
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onChangeTheme(appTheme)
            }
            .padding(horizontal = 40.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        RadioButton(
            selected = appTheme == currentAppTheme,
            onClick = null,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.secondary
            )
        )

        Text(
            text = stringResource(id = displayedName()),
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 16.sp
        )

    }
}