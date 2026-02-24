package com.example.witj_proyecto.data


data class Palabra(
    val id: Int,
    val textoLengua: String,     // Palabra en la lengua originaria
    val traduccion: String,      // Traducción en español
    val lengua: Lengua,
    val categoria: Categoria,
    val imagenRes: Int? = null,  // Recurso drawable asociado (opcional)
    val audioPath: String? = null // Ruta/URI del audio de pronunciación (opcional, para futuro)
)



enum class Lengua(val displayName: String) {
    Zapoteco("Zapoteco"),
    Nahuatl("Náhuatl"),
    Maya("Maya"),
    Mixteco("Mixteco")
}

