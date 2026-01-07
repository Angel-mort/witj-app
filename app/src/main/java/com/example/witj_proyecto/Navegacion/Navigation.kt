package com.example.witj_proyecto.Navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.witj_proyecto.pantallas.AprendeScreen
import com.example.witj_proyecto.pantallas.HistoriasScreen
import com.example.witj_proyecto.pantallas.InicioScreen
import com.example.witj_proyecto.pantallas.JuegosScreen
import com.example.witj_proyecto.pantallas.MenuScreen
import com.example.witj_proyecto.pantallas.pantallaSplash

@Composable
fun Navegacion (){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = rutas.Splash.route){

        //Ruta de la primer pantalla (Splash)
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
                onAprende = { navController.navigate(rutas.Aprende.route) },
                onJuegos = { navController.navigate(rutas.Juegos.route) },
                onHistorias = { navController.navigate(rutas.Historias.route) }
            )
        }
        composable(rutas.Aprende.route) { AprendeScreen() }
        composable(rutas.Juegos.route) {  JuegosScreen() }
        composable(rutas.Historias.route) { HistoriasScreen() }
    }
}