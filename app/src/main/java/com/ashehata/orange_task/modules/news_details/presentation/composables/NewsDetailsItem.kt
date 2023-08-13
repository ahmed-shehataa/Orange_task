package com.ashehata.orange_task.modules.news_details.presentation.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NewsDetailsItem(
    @StringRes title: Int,
    description: String,
    hasDivider: Boolean = true
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column {
            Text(
                stringResource(id = title) + ": ",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                description,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
            )
        }

        if (hasDivider)
            Divider(
                modifier = Modifier.fillMaxWidth(0.5f),
                color = MaterialTheme.colorScheme.secondary
            )
    }

}