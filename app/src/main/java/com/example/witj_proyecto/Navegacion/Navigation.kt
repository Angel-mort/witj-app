package com.example.witj_proyecto.Navegacion

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.witj_proyecto.viewmodel.DiccionarioViewModel
import com.example.witj_proyecto.viewmodel.JuegoViewModel
import com.example.witj_proyecto.pantallas.AprendeScreen
import com.example.witj_proyecto.pantallas.DiccionarioScreen
import com.example.witj_proyecto.pantallas.InicioScreen
import com.example.witj_proyecto.pantallas.JuegosScreen
import com.example.witj_proyecto.pantallas.MenuScreen
import com.example.witj_proyecto.pantallas.pantallaSplash
import com.example.witj_proyecto.ui.components.WitjScaffoldWithBottomNav

@Composable
fun Navegacion() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = rutas.Splash.route) {
        composable(rutas.Splash.route) {
            pantallaSplash {
                navController.navigate(rutas.Menu.route)
            }
        }
        composable(rutas.Inicio.route) {
            InicioScreen {
                navController.navigate(rutas.Menu.route)
            }
        }
        composable(rutas.Menu.route) {
            MenuScreen(

                onJuegos = { navController.navigate(rutas.Juegos.route) },
                onDiccionario = {navController.navigate(rutas.Diccionario.route)}

            )
        }

        // diccionario y juego llevan bottom bar y su viewModel
        composable(rutas.Diccionario.route) {
            val diccionarioViewModel: DiccionarioViewModel = viewModel()
            WitjScaffoldWithBottomNav(
                currentRoute = rutas.Diccionario.route,
                onNavigate = { route -> navController.navigate(route) { launchSingleTop = true } }
            ) {
                DiccionarioScreen(viewModel = diccionarioViewModel)
            }
        }
        composable(rutas.Juegos.route) {
            val juegoViewModel: JuegoViewModel = viewModel()
            WitjScaffoldWithBottomNav(
                currentRoute = rutas.Juegos.route,
                onNavigate = { route -> navController.navigate(route) { launchSingleTop = true } }
            ) {
                JuegosScreen(viewModel = juegoViewModel)
            }
        }
        composable(rutas.Aprende.route) { AprendeScreen() }
    }
}
