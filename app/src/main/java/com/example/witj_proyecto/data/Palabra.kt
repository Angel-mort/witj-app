package com.example.witj_proyecto.data


// una entrada del diccionario: palabra en lengua + español, opcional imagen y audio
data class Palabra(
    val id: Int,
    val textoLengua: String,
    val traduccion: String,
    val lengua: Lengua,
    val categoria: Categoria,
    val imagenRes: Int? = null,
    val audioPath: String? = null
)



enum class Lengua(val displayName: String) {
    Zapoteco("Zapoteco"),
    Nahuatl("Náhuatl"),
    Maya("Maya"),
    Mixteco("Mixteco")
}

