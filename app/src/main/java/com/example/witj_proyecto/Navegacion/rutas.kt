package com.example.witj_proyecto.Navegacion

// rutas de la app, una por pantalla
sealed class rutas(val route: String) {
    object Splash: rutas ("splash")
    object Inicio : rutas("inicio")
    object Menu : rutas("menu")

    object Juegos : rutas("juegos")

    object Diccionario: rutas("diccionario")
    object Aprende: rutas("aprende")
}