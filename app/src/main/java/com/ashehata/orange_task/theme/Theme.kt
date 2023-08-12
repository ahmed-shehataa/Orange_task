package com.ashehata.orange_task.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.ashehata.orange_task.database.models.AppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


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
    content: @Composable () -> Unit
) {

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

            content()
        }
    )
}