package com.example.witj_proyecto.data

/**
 * Estado de una partida del minijuego quiz.
 * @param indicePreguntaactual índice de la pregunta actual (0-based).
 * @param puntaje puntos acumulados.
 * @param terminado true cuando se alcanza el número total de preguntas.
 */
data class EstadoJuego(
    val indicePreguntaactual: Int = 0,
    val puntaje: Int = 0,
    val terminado: Boolean = false
)

/**
 * Representación de una pregunta del quiz (opcional, para lógica alternativa).
 */
data class Question(
    val palabra: String,
    val opciones: List<String>,
    val respuestacorrecta: String
)
