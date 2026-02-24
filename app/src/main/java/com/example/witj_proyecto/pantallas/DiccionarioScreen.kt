package com.example.witj_proyecto.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.witj_proyecto.R
import com.example.witj_proyecto.data.Categoria
import com.example.witj_proyecto.data.Lengua
import com.example.witj_proyecto.data.Palabra
import com.example.witj_proyecto.ui.components.SelectableChip
import com.example.witj_proyecto.ui.theme.WitjCardBackground
import com.example.witj_proyecto.ui.theme.WitjPrimary
import com.example.witj_proyecto.viewmodel.DiccionarioViewModel

// drawables que se pueden elegir para una palabra
private val IMAGENES_OPCIONES = listOf(
    R.drawable.verb1 to "Verbo 1",
    R.drawable.verb2 to "Verbo 2",
    R.drawable.verb3 to "Verbo 3",
    R.drawable.nat1 to "Naturaleza 1",
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DiccionarioScreen(
    viewModel: DiccionarioViewModel = viewModel()
) {
    val lengua by viewModel.lenguaSeleccionada.collectAsState()
    val categoria by viewModel.categoriaSeleccionada.collectAsState()
    val palabras by viewModel.palabras.collectAsState()
    val textoLengua by viewModel.textoLengua.collectAsState()
    val traduccion by viewModel.traduccion.collectAsState()
    val imagenResId by viewModel.imagenResId.collectAsState()

    // todo en un column con scroll (lista abajo sin lazy para no crashear)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .padding(bottom = 16.dp)
    ) {
        Text(
            "Diccionario Colaborativo",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "Selecciona una lengua",
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Lengua.entries.forEach { l ->
                SelectableChip(
                    text = l.displayName,
                    selected = lengua == l,
                    onClick = { viewModel.seleccionarLengua(l) }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // card del formulario para agregar palabra
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = WitjCardBackground),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Agregar Nueva Palabra",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = textoLengua,
                    onValueChange = viewModel::actualizarTextoLengua,
                    label = { Text("Palabra en ${lengua.displayName}") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = traduccion,
                    onValueChange = viewModel::actualizarTraduccion,
                    label = { Text("Palabra en Español") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Selecciona una categoría",
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.height(6.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Categoria.entries.forEach { c ->
                        SelectableChip(
                            text = c.displayName,
                            selected = categoria == c,
                            onClick = { viewModel.seleccionarCategoria(c) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Seleccionar Imagen", color = WitjPrimary)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {

                    IMAGENES_OPCIONES.forEach { (resId, label) ->
                        val selected = imagenResId == resId
                        SelectableChip(
                            text = label,
                            selected = selected,
                            onClick = { viewModel.seleccionarImagen(if (selected) null else resId) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                androidx.compose.material3.Button(
                    onClick = { /* TODO: grabar audio */ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = WitjPrimary)
                ) {
                    Text("Grabar Audio")
                }
                Spacer(modifier = Modifier.height(8.dp))
                androidx.compose.material3.Button(
                    onClick = { viewModel.agregarPalabra() },
                    modifier = Modifier.fillMaxWidth(),
                    colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = WitjPrimary)
                ) {
                    Text("Guardar Palabra")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "Palabras Guardadas (${palabras.size})",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        palabras.forEach { palabra ->
            PalabraItem(palabra = palabra)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

// una fila de la lista de palabras guardadas
@Composable
private fun PalabraItem(palabra: Palabra) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            palabra.imagenRes?.let { resId ->
                Image(
                    painter = painterResource(id = resId),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp)
                )
            }
            Column {
                Text(
                    palabra.textoLengua,
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    palabra.traduccion,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
