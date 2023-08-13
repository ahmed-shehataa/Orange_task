package com.ashehata.orange_task.util

import android.content.Context
import android.content.Intent
import android.net.Uri


fun Context.openLink(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
    })
}