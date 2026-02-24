package com.example.witj_proyecto

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.witj_proyecto.Navegacion.Navegacion

/**
 * Composable raíz de la app Witj.
 * Usa Scaffold como estructura principal; la navegación (NavHost) ocupa el contenido.
 * Permite añadir después TopBar, BottomBar o Drawer sin tocar las pantallas.
 */
@Composable
fun WitjApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { _ ->
        Surface(modifier = Modifier.fillMaxSize()) {
            Navegacion()
        }
    }
}
