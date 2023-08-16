package com.ashehata.orange_task.modules.settings.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ashehata.orange_task.R
import com.ashehata.orange_task.database.models.AppLocal

@Composable
fun RadioLocalItem(
    appLocal: AppLocal,
    currentAppLocal: AppLocal,
    onChangeLocal: (AppLocal) -> Unit
) {

    val displayedName: () -> Int = remember(appLocal) {
        {
            when (appLocal) {
                AppLocal.AR -> R.string.arabic
                AppLocal.EN -> R.string.english
            }
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onChangeLocal(appLocal)
            }
            .padding(horizontal = 40.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        RadioButton(
            selected = appLocal == currentAppLocal,
            onClick = null,
        )

        Text(
            text = stringResource(id = displayedName()),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 16.sp
        )

    }
}