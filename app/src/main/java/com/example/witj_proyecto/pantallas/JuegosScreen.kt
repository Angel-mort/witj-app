package com.example.witj_proyecto.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.witj_proyecto.data.Categoria
import com.example.witj_proyecto.data.Lengua
import com.example.witj_proyecto.viewmodel.JuegoViewModel


@Composable
fun JuegosScreen(viewModel: JuegoViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {


    val estado = viewModel.estado.value


    if (estado.terminado) {
        ResultadoScreen(puntaje = estado.puntaje,
            onReiniciar = { viewModel.reiniciarJuego() }
        )
    } else {

        val palabra = viewModel.obtenerPalabraActual()
        val opciones = viewModel.obtenerOpciones()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            //  Selector de lengua
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { viewModel.seleccionarLengua(Lengua.Zapoteco) }) {
                    Text("Zapoteco")
                }
                Button(onClick = { viewModel.seleccionarLengua(Lengua.Mixteco) }) {
                    Text("Mixteco")
                }
            }

            //  Selector de categoría
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = { viewModel.seleccionarCategoria(Categoria.naturaleza) }) {
                    Text("Naturaleza")
                }
                Button(onClick = { viewModel.seleccionarCategoria(Categoria.verbo) }) {
                    Text("Verbo")
                }
            }
            palabra.imagenRes?.let { imagen ->
                Image(
                    painter = painterResource(id = imagen),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
            }


            Text("Palabra:")
            Text(text = palabra.textoLengua, style = MaterialTheme.typography.headlineMedium)

            //opciones de respuesta
            opciones.forEach { opcion ->
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { viewModel.seleccionarOpcion(opcion) }
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

