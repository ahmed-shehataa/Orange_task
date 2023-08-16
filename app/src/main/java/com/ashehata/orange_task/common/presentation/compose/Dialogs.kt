package com.ashehata.orange_task.common.presentation.compose

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.DialogProperties

@Composable
fun AlertDialog(
    state: MutableState<Boolean>,
    @StringRes title: Int,
    @StringRes content: Int,
    @StringRes positiveTitleRes: Int,
    @StringRes negativeTitleRes: Int,
    positive: (() -> Unit)? = null,
    negative: (() -> Unit)? = null,
    isCancellable: Boolean = true
) {

    if (state.value) {
        AlertDialog(
            properties = DialogProperties(
                dismissOnClickOutside = isCancellable,
            ),
            containerColor = MaterialTheme.colorScheme.primary,
            onDismissRequest = {
                state.value = false
            },
            title = {
                Text(
                    text = stringResource(id = title),
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            text = {
                Text(
                    text = stringResource(id = content),
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            confirmButton = {
                TextButton(
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                    onClick = {
                        if (positive != null) {
                            positive()
                        }
                        state.value = false
                    }
                ) {
                    Text(
                        text = stringResource(id = positiveTitleRes),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            },
            dismissButton = {
                TextButton(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.5f
                        )
                    ),
                    onClick = {
                        if (negative != null) {
                            negative()
                        }
                        state.value = false
                    }
                ) {
                    Text(
                        text = stringResource(id = negativeTitleRes),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        )
    }
}
