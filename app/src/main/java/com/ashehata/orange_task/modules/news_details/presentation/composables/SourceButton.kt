package com.ashehata.orange_task.modules.news_details.presentation.composables

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.ashehata.orange_task.R
import com.ashehata.orange_task.util.DoIf

@Composable
fun SourceButton(modifier: Modifier, url: String?, onOpenSourceClicked: (String) -> Unit) {
    url.DoIf {
        Button(
            modifier = modifier,
            onClick = {
                onOpenSourceClicked(it)
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text(
                text = stringResource(id = R.string.open_source),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primaryContainer
            )
        }
    }
}