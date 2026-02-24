package com.example.witj_proyecto.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.witj_proyecto.data.Categoria
import com.example.witj_proyecto.data.Lengua
import com.example.witj_proyecto.ui.components.SelectableChip
import com.example.witj_proyecto.ui.theme.WitjPrimary
import com.example.witj_proyecto.ui.theme.WitjWordBox
import com.example.witj_proyecto.viewmodel.JuegoViewModel
import androidx.compose.foundation.Image
import androidx.compose.runtime.remember

@Composable
fun JuegosScreen(viewModel: JuegoViewModel = viewModel()) {
    val estado = viewModel.estado.value
    val lengua by viewModel.lenguaSeleccionadaState
    val categoria by viewModel.categoriaSeleccionadaState

    when {
        estado.terminado -> ResultadoScreen(
            puntaje = estado.puntaje,
            totalPreguntas = viewModel.getTotalPreguntas(),
            onReiniciar = { viewModel.reiniciarJuego() }
        )
        !viewModel.tienePreguntas() -> ConfiguraJuegoContent(
            lengua = lengua,
            categoria = categoria,
            onLengua = viewModel::seleccionarLengua,
            onCategoria = viewModel::seleccionarCategoria,
            onIniciarJuego = viewModel::iniciarJuego
        )
        else -> QuizContent(
            viewModel = viewModel,
            estado = estado
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ConfiguraJuegoContent(
    lengua: Lengua,
    categoria: Categoria,
    onLengua: (Lengua) -> Unit,
    onCategoria: (Categoria) -> Unit,
    onIniciarJuego: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        Text(
            "Juego de Palabras",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            "Configura tu Juego",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Selecciona una lengua",
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Lengua.entries.forEach { l ->
                SelectableChip(
                    text = l.displayName,
                    selected = lengua == l,
                    onClick = { onLengua(l) }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "Selecciona una categoría",
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Categoria.entries.forEach { c ->
                SelectableChip(
                    text = c.displayName,
                    selected = categoria == c,
                    onClick = { onCategoria(c) }
                )
            }
        }
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onIniciarJuego,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = WitjPrimary)
        ) {
            Text("Iniciar Juego")
        }
    }
}

@Composable
private fun QuizContent(
    viewModel: JuegoViewModel,
    estado: com.example.witj_proyecto.data.EstadoJuego
) {
    val palabra = viewModel.obtenerPalabraActual()
    val opciones = remember(estado.indicePreguntaactual) { viewModel.obtenerOpciones() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        Text(
            "Juego de Palabras",
            style = MaterialTheme.typography.titleLarge
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Pregunta ${estado.indicePreguntaactual + 1} de ${viewModel.getTotalPreguntas()}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            "Puntaje: ${estado.puntaje}",
            style = MaterialTheme.typography.bodyMedium,
            color = WitjPrimary
        )
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(WitjWordBox, RoundedCornerShape(12.dp))
                .padding(24.dp)
        ) {
            Text(
                text = palabra.textoLengua,
                style = MaterialTheme.typography.headlineMedium,
                color = WitjPrimary
            )
        }
        palabra.imagenRes?.let { resId ->
            Spacer(modifier = Modifier.height(12.dp))
            Image(
                painter = painterResource(id = resId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "¿Cuál es la traducción?",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(12.dp))
        opciones.forEach { opcion ->
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                onClick = { viewModel.seleccionarOpcion(opcion) },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = WitjWordBox),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
            ) {
                Text(
                    opcion,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ResultadoScreen(
    puntaje: Int,
    totalPreguntas: Int = 0,
    onReiniciar: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Juego terminado\nPuntaje: $puntaje${if (totalPreguntas > 0) " / $totalPreguntas" else ""}",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onReiniciar,
                colors = ButtonDefaults.buttonColors(containerColor = WitjPrimary)
            ) {
                Text("Volver a jugar")
            }
        }
    }
}
