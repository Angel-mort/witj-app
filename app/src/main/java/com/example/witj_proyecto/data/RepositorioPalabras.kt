package com.example.witj_proyecto.data

import com.example.witj_proyecto.R

//fuente unica de palabras q se usaran en la app

class RepositorioPalabras {

    //lista temporal en memoria  (despues podriamos usar bases de datos)
    private val palabras= listOf(
        Palabra(zapoteco = "Gozedo",
            espanol = "Aprendizaje",
            imagenRes = R.drawable.verb1,
            categoria = "verbo"
        ),
        Palabra(zapoteco = "chaac", espanol = "Lluvia", imagenRes = R.drawable.nat1,"Naturaleza")

    )

    //Funcion para devolver todas las palabras

    fun obtenerTodas(): List<Palabra> {
        return palabras
    }
    //Funcion para devolver palabras filtradas por categoria
    fun obtenerXcategoria(categoria: String): List<Palabra> {
        return palabras.filter { it.categoria== categoria }
    }

    //funcion para devolver una cantidad aleatoria para el minijuego
    fun obtenerAleatorias(cantidad: Int): List<Palabra> {
        return palabras.shuffled().take(cantidad)
    }

}

