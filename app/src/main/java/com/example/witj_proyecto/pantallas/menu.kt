package com.example.witj_proyecto.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MenuScreen(
    onAprende: () -> Unit,
    onJuegos: () -> Unit,
    onHistorias: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(onClick = onAprende) { Text("Aprender") }
        Button(onClick = onJuegos) { Text("Jugar") }
        Button(onClick = onHistorias) { Text("Historias") }
    }
}
