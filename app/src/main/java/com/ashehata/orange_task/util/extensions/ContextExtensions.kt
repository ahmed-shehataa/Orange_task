package com.ashehata.orange_task.util.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.ashehata.orange_task.database.models.AppLocal
import java.util.Locale


fun Context.openLink(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
    })
}

fun Context.changeLocal(appLocal: AppLocal) {
    val locale = Locale(appLocal.name)
    Locale.setDefault(locale)
    val resources = this.resources
    val configuration = resources.configuration
    configuration.locale = locale
    resources.updateConfiguration(configuration, resources.displayMetrics)
}
