package com.ashehata.orange_task.modules.settings.presentation.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SettingsItem(
    @DrawableRes iconRes: Int,
    @StringRes titleRes: Int,
    isExpanded: MutableState<Boolean> = mutableStateOf(false),
    onClicked: () -> Unit = {},
    expandedList: @Composable (ColumnScope) -> Unit,
) {

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClicked()
                }
                .padding(
                    horizontal = 20.dp,
                    vertical = 20.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Icon(
                modifier = Modifier.size(26.dp),
                imageVector = ImageVector.vectorResource(id = iconRes),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )

            Text(
                text = stringResource(id = titleRes),
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 16.sp
            )

        }

        AnimatedVisibility(
            visible = isExpanded.value,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            expandedList(this@Column)
        }
    }
}