package com.example.witj_proyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.witj_proyecto.Navegacion.Navegacion
import com.example.witj_proyecto.ui.theme.Witj_proyectoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Witj_proyectoTheme {
                Surface {
                    Navegacion() //llamar funcion q controla navegaciones
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Witj_proyectoTheme {
        Navegacion()
    }
}