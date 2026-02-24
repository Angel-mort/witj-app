package com.example.witj_proyecto.data

import com.example.witj_proyecto.R

// lista de palabras en memoria, compartida entre diccionario y juego (singleton)
class RepositorioPalabras private constructor() {

    companion object {
        @Volatile
        private var instance: RepositorioPalabras? = null
        fun getInstance(): RepositorioPalabras = instance ?: synchronized(this) {
            instance ?: RepositorioPalabras().also { instance = it }
        }
    }

    private val palabrasIniciales = listOf(
        Palabra(1, "Gozedo", "Aprendizaje", lengua = Lengua.Zapoteco, categoria = Categoria.verbo, R.drawable.verb1),
        Palabra(2, "chaac", "Lluvia", lengua = Lengua.Zapoteco, categoria = Categoria.naturaleza, R.drawable.nat1),
        Palabra(3, "Gudao", "Comer", lengua = Lengua.Zapoteco, categoria = Categoria.verbo, R.drawable.verb2),
        Palabra(4, "Ye'e", "Beber", lengua = Lengua.Zapoteco, categoria = Categoria.verbo, R.drawable.verb3),
    )
    private val palabras = palabrasIniciales.toMutableList()
    private var nextId = palabrasIniciales.maxOfOrNull { it.id }?.plus(1) ?: 1





    fun obtenerTodas(): List<Palabra> = palabras.toList()

    // agrega palabra nueva (por ahora solo en memoria)
    fun agregarPalabra(palabra: Palabra) {
        val conId = if (palabra.id <= 0) palabra.copy(id = nextId++) else palabra
        palabras.add(conId)
    }

    fun obtenerPorLengua(lengua: Lengua): List<Palabra> =
        palabras.filter { it.lengua == lengua }

    // filtro por lengua y categoria (para el juego)
    fun obtenerXlenguaYcategoria(
        lengua: Lengua,
        categoria: Categoria
    ): List<Palabra> {
        return palabras.filter {   it.lengua == lengua && it.categoria == categoria}
    }

    // toma N palabras al azar (cuando no hay suficientes filtradas)
    fun obtenerAleatorias(cantidad: Int): List<Palabra> {
        return palabras.shuffled().take(cantidad)
    }

}

