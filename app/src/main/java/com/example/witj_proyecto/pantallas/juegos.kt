package com.example.witj_proyecto.pantallas

import androidx.compose.runtime.Composable

private const val TOTAL_QUESTIONS = 3

data class Question(
    val palabra: String,
    val opciones: List<String>,
    val respuestacorrecta: String
)
data class estadoJuego(
    val indicePreguntaactual: Int = 0,
    val puntaje: Int = 0,
    val terminado: Boolean = false
)

fun checarRespuesta(
    seleccionado: String,
    question: Question,
    estado: estadoJuego
): estadoJuego {
    val nuevopuntaje = if (seleccionado == question.respuestacorrecta)
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


val preguntasDemo = listOf(
    Question(
        palabra = "Witj",
        opciones = listOf("Aprender", "Jugar", "Comer"),
        respuestacorrecta = "Aprender"
    ),
    Question(
        palabra = "Bida",
        opciones = listOf("Casa", "Perro", "Maíz"),
        respuestacorrecta = "Casa"
    ),
    Question(
        palabra = "Guii",
        opciones = listOf("Flor", "Sol", "Agua"),
        respuestacorrecta = "Sol"
    )
)

@Composable
fun JuegosScreen() {
    // pantalla juegos
}