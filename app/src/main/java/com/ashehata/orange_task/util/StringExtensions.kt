package com.ashehata.orange_task.util

import androidx.compose.runtime.Composable


@Composable
fun String?.DoIf(call: @Composable (String) -> Unit) {
    if (this.isNullOrEmpty().not())
        call(this ?: "")
}

fun String?.doIf(call: (String) -> Unit) {
    if (this.isNullOrEmpty().not())
        call(this ?: "")
}