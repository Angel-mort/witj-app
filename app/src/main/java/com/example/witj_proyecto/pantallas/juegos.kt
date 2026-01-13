package com.example.witj_proyecto.pantallas


//total de palabras por partida
private const val TOTAL_QUESTIONS = 3

data class Question(
    val palabra: String,
    val opciones: List<String>,
    val respuestacorrecta: String
)
data class EstadoJuego(
    val indicePreguntaactual: Int = 0,
    val puntaje: Int = 0,
    val terminado: Boolean = false
)

fun checarRespuesta(
    seleccionado: String,
    respuestacorrecta: String,
    estado: EstadoJuego
): EstadoJuego {
    //si la respuesta es correcta, aumenta el puntaje
    val nuevopuntaje = if (seleccionado == respuestacorrecta)
        estado.puntaje+ 1
    else estado.puntaje

    val nextIndex = estado.indicePreguntaactual + 1

    return if (nextIndex >= TOTAL_QUESTIONS) {
        estado.copy(puntaje = nuevopuntaje, terminado = true)
    } else {
        estado.copy(
            indicePreguntaactual = nextIndex,
            puntaje = nuevopuntaje
        )
    }
}


