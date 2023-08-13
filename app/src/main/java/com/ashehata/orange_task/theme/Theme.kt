package com.ashehata.orange_task.theme

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.ashehata.orange_task.database.models.AppLocal
import com.ashehata.orange_task.database.models.AppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.util.Locale


private val LightColorScheme = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
)
private val DarkColorScheme = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    secondary = md_theme_dark_secondary
)

@Composable
fun AppTheme(
    appTheme: AppTheme = AppTheme.SYSTEM,
    appLocal: AppLocal = AppLocal.EN,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()
    val colorScheme = when (appTheme) {
        AppTheme.SYSTEM -> {
            if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
        }
        AppTheme.LIGHT -> LightColorScheme
        AppTheme.DARK -> DarkColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = {
            // Control the status bar color
            val primaryColor = MaterialTheme.colorScheme.primary
            DisposableEffect(systemUiController, useDarkIcons, colorScheme) {
                systemUiController.setStatusBarColor(color = primaryColor)
                onDispose {}
            }

            LaunchedEffect(key1 = appLocal) {
                context.changeLocal(appLocal)
            }

            content()
        }
    )
}

private fun Context.changeLocal(appLocal: AppLocal) {
    val locale = Locale(appLocal.name)
    Locale.setDefault(locale)
    val resources = this.resources
    val configuration = resources.configuration
    configuration.locale = locale
    resources.updateConfiguration(configuration, resources.displayMetrics)
}