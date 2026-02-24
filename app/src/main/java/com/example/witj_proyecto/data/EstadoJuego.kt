package com.example.witj_proyecto.data

// estado del quiz: en que pregunta va, puntaje y si ya termino
data class EstadoJuego(
    val indicePreguntaactual: Int = 0,
    val puntaje: Int = 0,
    val terminado: Boolean = false
)

// pregunta individual (palabra, opciones, respuesta correcta)
data class Question(
    val palabra: String,
    val opciones: List<String>,
    val respuestacorrecta: String
)
