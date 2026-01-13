package com.example.witj_proyecto.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.witj_proyecto.data.Categoria
import com.example.witj_proyecto.data.Lengua
import com.example.witj_proyecto.data.Palabra
import com.example.witj_proyecto.data.RepositorioPalabras
import com.example.witj_proyecto.pantallas.EstadoJuego
import com.example.witj_proyecto.pantallas.checarRespuesta

class JuegoViewModel : ViewModel() {


    // Repositorio: fuente unica de palabras
    private val repositorio= RepositorioPalabras()

    // Lengua y categoría seleccionadas (estado del filtro)
    private var lenguaSeleccionada: Lengua = Lengua.Zapoteco
    private var categoriaSeleccionada: Categoria = Categoria.naturaleza

    //Estado del juego
    var estado = mutableStateOf(EstadoJuego())
        private set
    ////////////////////////////////////////////

    //lista de palabras que se usaran en la partida
    private var palabrasJuego: List<Palabra> = emptyList()

    init {
        // Carga inicial del juego
        cargarPalabras()
    }

    // Carga palabras según lengua y categoría
    private fun cargarPalabras() {
        palabrasJuego = repositorio
            .obtenerXlenguaYcategoria(
                lengua = lenguaSeleccionada,
                categoria = categoriaSeleccionada
            )
            .shuffled()
            .take(5)

        estado.value = EstadoJuego()
    }
    // Permite cambiar la lengua desde la UI
    fun seleccionarLengua(lengua: Lengua) {
        lenguaSeleccionada = lengua
        cargarPalabras()
    }

    // Permite cambiar la categoría desde la UI
    fun seleccionarCategoria(categoria: Categoria) {
        categoriaSeleccionada = categoria
        cargarPalabras()
    }
    ////////////////////////////////////////////
    // Obtener la palabra actual según el índice del juego
    fun obtenerPalabraActual(): Palabra {
        return palabrasJuego[estado.value.indicePreguntaactual]
    }

    ///////////////////////////////////////////////////////////////
    //funcion q se llamara cuando el usuario seleccione una opcionm
    fun seleccionarOpcion(opcion: String){
        val palabraActual= obtenerPalabraActual()

        //comparar con la traduccion correcta
        estado.value= checarRespuesta(
            seleccionado = opcion,
            respuestacorrecta= palabraActual.traduccion,
            estado = estado.value
        )
    }

    //////////////////////////////////////////////////////////77
    fun reiniciarJuego() {
        estado.value = EstadoJuego()
    }
    //Devuelve las opciones mezcladas (correcta + distractores)
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
