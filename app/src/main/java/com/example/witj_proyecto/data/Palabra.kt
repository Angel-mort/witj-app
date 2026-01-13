package com.example.witj_proyecto.data


data class Palabra(
    val id: Int,
    val textoLengua: String,     // Palabra en la lengua
    val traduccion: String,      // traduccion en Español
    val lengua: Lengua,          // Zapoteco, Mixteco, etc.
    val categoria: Categoria,    // Animales, Naturaleza, etc..
    val imagenRes: Int? = null   // Imagen asociada opcional
)



enum class  Lengua {
   Zapoteco,
    Mixteco
    // más lenguas en el futuro
}

