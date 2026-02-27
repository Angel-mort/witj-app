package com.example.witj_proyecto.ui.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.witj_proyecto.ui.theme.WitjBgNatural
import com.example.witj_proyecto.ui.theme.WitjNavActive
import com.example.witj_proyecto.ui.theme.WitjNavInactive

// tabs del bottom bar: solo texto, sin usar resources para evitar errores raros en el ide
enum class WitjTab(
    val route: String,
    val label: String
) {
    Diccionario("diccionario", "Diccionario"),
    Juego("juegos", "Juego")
}

// barra de abajo con tabs de diccionario y juego
@Composable
fun WitjBottomNavBar(
    currentRoute: String,
    onTabSelected: (String) -> Unit
) {
    NavigationBar(
        containerColor = WitjBgNatural,
        tonalElevation = 8.dp
    ) {
        for (tab in WitjTab.entries) {
            val selected = currentRoute == tab.route
            NavigationBarItem(
                selected = selected,
                onClick = { onTabSelected(tab.route) },
                icon = {
                    // no usamos iconos, solo dejamos el label
                },
                label = {
                    Text(
                        text = tab.label,
                        color = if (selected) WitjNavActive else WitjNavInactive
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = WitjNavActive,
                    selectedTextColor = WitjNavActive,
                    unselectedIconColor = WitjNavInactive,
                    unselectedTextColor = WitjNavInactive
                )
            )
        }
    }
}
