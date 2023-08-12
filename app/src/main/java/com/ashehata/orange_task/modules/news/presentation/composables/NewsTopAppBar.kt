package com.ashehata.orange_task.modules.news.presentation.composables

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.ashehata.orange_task.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopAppBar(onSettingsClicked: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(id = R.string.news),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(),
        actions = {

            IconButton(onClick = {
                onSettingsClicked()
            }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings",
                )
            }
        },
    )
}