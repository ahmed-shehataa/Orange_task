package com.ashehata.orange_task.util.extensions

import android.util.LayoutDirection
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.core.text.layoutDirection
import java.util.Locale

@Stable
fun Modifier.autoMirror(): Modifier {
    return if (Locale.getDefault().layoutDirection == LayoutDirection.RTL)
        this.scale(scaleX = -1f, scaleY = 1f)
    else
        this
}