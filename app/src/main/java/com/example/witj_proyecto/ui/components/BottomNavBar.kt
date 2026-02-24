package com.example.witj_proyecto.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.witj_proyecto.R
import com.example.witj_proyecto.ui.theme.WitjNavActive
import com.example.witj_proyecto.ui.theme.WitjNavInactive

// tabs del bottom bar: diccionario y juego
enum class WitjTab(
    val route: String,
    val labelRes: Int,
    val icon: ImageVector
) {
    Diccionario("diccionario", R.string.tab_diccionario, Icons.Outlined.MenuBook),
    Juego("juegos", R.string.tab_juego, Icons.Outlined.SportsEsports)
}

// barra de abajo con iconos diccionario y juego, el activo en rojo
@Composable
fun WitjBottomNavBar(
    currentRoute: String,
    onTabSelected: (String) -> Unit
) {
    NavigationBar(
        containerColor = Color(0xFFF5F5F5),
        tonalElevation = 8.dp
    ) {
        for (tab in WitjTab.entries) {
            val selected = currentRoute == tab.route
            NavigationBarItem(
                selected = selected,
                onClick = { onTabSelected(tab.route) },
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = stringResource(tab.labelRes),
                        tint = if (selected) WitjNavActive else WitjNavInactive
                    )
                },
                label = {
                    Text(
                        text = stringResource(tab.labelRes),
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
