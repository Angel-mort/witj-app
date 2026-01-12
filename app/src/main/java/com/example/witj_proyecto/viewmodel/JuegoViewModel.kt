package com.example.witj_proyecto.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.witj_proyecto.pantallas.*

class JuegoViewModel : ViewModel() {

    var estado = mutableStateOf(EstadoJuego())
        private set

    val preguntas = preguntasDemo

    fun onOpcionSeleccionada(opcion: String) {
        val preguntaActual = preguntas[estado.value.indicePreguntaactual]

        estado.value = checarRespuesta(
            seleccionado = opcion,
            question = preguntaActual,
            estado = estado.value
        )
    }

    fun reiniciarJuego() {
        estado.value = EstadoJuego()
    }
}
