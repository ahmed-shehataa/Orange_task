package com.ashehata.orange_task.util.extensions

import android.app.Activity
import android.content.Intent


fun Activity.restartActivity() {
    overridePendingTransition(0, 0)
    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
    finish()
    overridePendingTransition(0, 0)
    startActivity(intent)

}