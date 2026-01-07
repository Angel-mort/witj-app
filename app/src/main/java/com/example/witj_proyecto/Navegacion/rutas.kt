package com.example.witj_proyecto.Navegacion

sealed class rutas (val route: String){
    object Splash: rutas ("splash")
    object Inicio : rutas("inicio")
    object Menu : rutas("menu")
    object Aprende : rutas("aprende")
    object Juegos : rutas("juegos")
    object Historias : rutas("historias")
}