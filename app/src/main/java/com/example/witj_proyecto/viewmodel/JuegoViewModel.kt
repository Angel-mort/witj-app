package com.example.witj_proyecto.viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.witj_proyecto.data.Categoria
import com.example.witj_proyecto.data.EstadoJuego
import com.example.witj_proyecto.data.Lengua
import com.example.witj_proyecto.data.Palabra
import com.example.witj_proyecto.data.RepositorioPalabras
import com.example.witj_proyecto.pantallas.checarRespuesta

// estado del quiz: config, preguntas, puntaje y opciones
class JuegoViewModel : ViewModel() {

    private val repositorio = RepositorioPalabras.getInstance()

    private val _lenguaSeleccionada = mutableStateOf(Lengua.Zapoteco)
    val lenguaSeleccionadaState: State<Lengua> = _lenguaSeleccionada

    private val _categoriaSeleccionada = mutableStateOf(Categoria.naturaleza)
    val categoriaSeleccionadaState: State<Categoria> = _categoriaSeleccionada

    var estado = mutableStateOf(EstadoJuego())
        private set

    private var palabrasJuego: List<Palabra> = emptyList()

    // el usuario eligio lengua/categoria y toco iniciar
    fun iniciarJuego() {
        cargarPalabras()
    }

    private fun cargarPalabras() {
        val filtradas = repositorio
            .obtenerXlenguaYcategoria(
                lengua = _lenguaSeleccionada.value,
                categoria = _categoriaSeleccionada.value
            )
            .shuffled()
        palabrasJuego = if (filtradas.size >= 3) filtradas.take(5) else repositorio.obtenerAleatorias(5)
        estado.value = EstadoJuego()
    }

    fun seleccionarLengua(lengua: Lengua) {
        _lenguaSeleccionada.value = lengua
    }

    fun seleccionarCategoria(categoria: Categoria) {
        _categoriaSeleccionada.value = categoria
    }
    fun tienePreguntas(): Boolean = palabrasJuego.isNotEmpty()

    fun getTotalPreguntas(): Int = palabrasJuego.size

    fun obtenerPalabraActual(): Palabra = palabrasJuego[estado.value.indicePreguntaactual]

    // usuario eligio una opcion, checamos y actualizamos estado
    fun seleccionarOpcion(opcion: String) {
        val palabraActual = obtenerPalabraActual()
        estado.value = checarRespuesta(
            seleccionado = opcion,
            respuestacorrecta = palabraActual.traduccion,
            estado = estado.value,
            totalPreguntas = palabrasJuego.size
        )
    }

    fun reiniciarJuego() {
        cargarPalabras()
    }

    // la correcta mas 2 distractores, todo mezclado
    fun obtenerOpciones(): List<String> {
        val correcta= obtenerPalabraActual().traduccion

        val distractores = repositorio.obtenerTodas()
            .map { it.traduccion }
            .filter { it != correcta }
            .shuffled()
            .take(2)
        return (distractores + correcta).shuffled()
    }

}
