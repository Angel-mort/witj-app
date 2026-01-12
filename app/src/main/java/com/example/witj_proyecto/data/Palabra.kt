package com.example.witj_proyecto.data

data class Palabra(
    val zapoteco: String,      // Palabra en zapoteco
    val espanol: String,       // Traduccion al español
    val imagenRes: Int,        // Imagen asociada (drawable)
    val categoria: String      // Categoria tematica
)