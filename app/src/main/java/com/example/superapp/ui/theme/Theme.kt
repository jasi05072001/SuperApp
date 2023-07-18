package com.example.superapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val darkColorScheme = darkColorScheme(
    primary = BlueGray,
    surface = Black,

    )

private val lightColorScheme = lightColorScheme(
    primary = Black,
    surface = Color.White
)

@Composable
fun SuperAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {


    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkColorScheme
        else -> lightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            if (darkTheme) {
                window.statusBarColor = Color(0xff1E293B).toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
                window.navigationBarColor = Color(0xff1E293B).toArgb()
            }else {
                window.statusBarColor = Color(0xffBFDBFE).toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
                window.navigationBarColor = Color(0xffBFDBFE).toArgb()

            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}