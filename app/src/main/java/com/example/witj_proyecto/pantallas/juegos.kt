package com.example.witj_proyecto.pantallas

import com.example.witj_proyecto.data.EstadoJuego

// revisa si acerto, suma punto y avanza o marca terminado
fun checarRespuesta(
    seleccionado: String,
    respuestacorrecta: String,
    estado: EstadoJuego,
    totalPreguntas: Int
): EstadoJuego {
    val nuevopuntaje = if (seleccionado == respuestacorrecta)
        estado.puntaje + 1
    else estado.puntaje

    val nextIndex = estado.indicePreguntaactual + 1

    return if (nextIndex >= totalPreguntas) {
        estado.copy(puntaje = nuevopuntaje, terminado = true)
    } else {
        estado.copy(
            indicePreguntaactual = nextIndex,
            puntaje = nuevopuntaje
        )
    }
}
