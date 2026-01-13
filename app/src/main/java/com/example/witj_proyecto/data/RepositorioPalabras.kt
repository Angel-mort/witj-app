package com.example.witj_proyecto.data

import com.example.witj_proyecto.R

//fuente unica de palabras q se usaran en la app

class RepositorioPalabras {

    //lista temporal en memoria  (despues podriamos usar bases de datos)
    private val palabras= listOf(
        Palabra(1,"Gozedo", "Aprendizaje",  lengua = Lengua.Zapoteco, categoria = Categoria.verbo, R.drawable.verb1 ),
        Palabra(2, "chaac", "Lluvia", lengua = Lengua.Zapoteco, categoria = Categoria.naturaleza, R.drawable.nat1),
        Palabra(3, "Gudao", "Comer", lengua = Lengua.Zapoteco, categoria = Categoria.verbo, R.drawable.verb2),
        Palabra(4, "Ye'e", "Beber", lengua = Lengua.Zapoteco, categoria = Categoria.verbo, R.drawable.verb3),


      //.......


    )





    //Funcion para devolver todas las palabras
    fun obtenerTodas(): List<Palabra> {
        return palabras
    }
    //Funcion para devolver palabras filtradas por categoria
    fun obtenerXlenguaYcategoria(
        lengua: Lengua,
        categoria: Categoria
    ): List<Palabra> {
        return palabras.filter {   it.lengua == lengua && it.categoria == categoria}
    }

    //funcion para devolver una cantidad aleatoria para el minijuego
    fun obtenerAleatorias(cantidad: Int): List<Palabra> {
        return palabras.shuffled().take(cantidad)
    }

}

