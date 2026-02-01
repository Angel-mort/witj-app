package com.example.witj_proyecto.Navegacion

/** Clase sellada donde definimos las rutas**/

sealed class rutas (val route: String){
    object Splash: rutas ("splash")
    object Inicio : rutas("inicio")
    object Menu : rutas("menu")

    object Juegos : rutas("juegos")

    object  Diccionario: rutas("diccionario")
}