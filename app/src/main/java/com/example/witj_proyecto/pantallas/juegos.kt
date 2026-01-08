package com.example.witj_proyecto.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.witj_proyecto.viewmodel.JuegoViewModel


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
    question: Question,
    estado: EstadoJuego
): EstadoJuego {
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
fun JuegosScreen(viewModel: JuegoViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val estado = viewModel.estado.value
    val preguntaActual = viewModel.preguntas[estado.indicePreguntaactual]

    if (estado.terminado) {
        ResultadoScreen(puntaje = estado.puntaje,
            onReiniciar = { viewModel.reiniciarJuego() }
            )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text("Palabra:")
            Text(preguntaActual.palabra, style = MaterialTheme.typography.headlineMedium)


            preguntaActual.opciones.forEach { opcion ->
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.onOpcionSeleccionada(opcion) }
                ) {
                    Text(opcion)
                }
            }
            Text(text = "Puntaje: ${estado.puntaje}")
        }
    }
}
@Composable
fun ResultadoScreen(puntaje: Int,
                    onReiniciar: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Juego terminado\nPuntaje: $puntaje",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onReiniciar) {
                Text("Volver a jugar")
            }
    }
}
    }

