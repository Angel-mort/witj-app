package com.example.witj_proyecto.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = WitjTurquesaSoft,
    secondary = WitjVerdeHoja,
    tertiary = WitjMagenta,
    background = Color(0xFF2B2621),
    surface = Color(0xFF2F2A25),
    onSurface = WitjBgCanvas,
    onBackground = WitjBgCanvas
)

private val LightColorScheme = lightColorScheme(
    // fondos (evitamos blanco puro)
    background = WitjBgCanvas,
    surface = WitjBgNatural,

    // acciones
    primary = WitjTurquesa,
    onPrimary = WitjBgCanvas,
    secondary = WitjVerdeHoja,
    onSecondary = WitjBgCanvas,
    tertiary = WitjMaiz,
    onTertiary = WitjTextCafe,

    // texto (evitamos negro puro)
    onSurface = WitjTextCafe,
    onBackground = WitjTextCafe,

    // extras utiles
    outline = WitjChipBorder,
    error = WitjRojoBarro,
    onError = WitjBgCanvas
)

// tema de la app, por ahora sin dynamic color
@Composable
fun Witj_proyectoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}