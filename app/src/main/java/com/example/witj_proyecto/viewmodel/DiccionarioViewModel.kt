package com.example.witj_proyecto.viewmodel

import androidx.lifecycle.ViewModel
import com.example.witj_proyecto.data.Categoria
import com.example.witj_proyecto.data.Lengua
import com.example.witj_proyecto.data.Palabra
import com.example.witj_proyecto.data.RepositorioPalabras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// estado del diccionario: lengua, lista, formulario y acciones
class DiccionarioViewModel(
    private val repositorio: RepositorioPalabras = RepositorioPalabras.getInstance()
) : ViewModel() {

    private val _lenguaSeleccionada = MutableStateFlow(Lengua.Zapoteco)
    val lenguaSeleccionada: StateFlow<Lengua> = _lenguaSeleccionada.asStateFlow()

    private val _palabras = MutableStateFlow<List<Palabra>>(emptyList())
    val palabras: StateFlow<List<Palabra>> = _palabras.asStateFlow()

    init {
        refrescarLista()
    }

    // campos del formulario
    private val _textoLengua = MutableStateFlow("")
    val textoLengua: StateFlow<String> = _textoLengua.asStateFlow()

    private val _traduccion = MutableStateFlow("")
    val traduccion: StateFlow<String> = _traduccion.asStateFlow()

    private val _categoriaSeleccionada = MutableStateFlow(Categoria.naturaleza)
    val categoriaSeleccionada: StateFlow<Categoria> = _categoriaSeleccionada.asStateFlow()

    private val _imagenResId = MutableStateFlow<Int?>(null)
    val imagenResId: StateFlow<Int?> = _imagenResId.asStateFlow()

    private val _audioPath = MutableStateFlow<String?>(null)
    val audioPath: StateFlow<String?> = _audioPath.asStateFlow()

    fun seleccionarLengua(lengua: Lengua) {
        _lenguaSeleccionada.value = lengua
        refrescarLista()
    }

    fun actualizarTextoLengua(texto: String) {
        _textoLengua.value = texto
    }

    fun actualizarTraduccion(texto: String) {
        _traduccion.value = texto
    }

    fun seleccionarCategoria(categoria: Categoria) {
        _categoriaSeleccionada.value = categoria
    }

    fun seleccionarImagen(resId: Int?) {
        _imagenResId.value = resId
    }

    // para cuando tengamos grabacion de audio
    fun asignarAudioGrabado(path: String?) {
        _audioPath.value = path
    }

    fun refrescarLista() {
        _palabras.value = repositorio
            .obtenerPorLengua(_lenguaSeleccionada.value)
    }

    fun agregarPalabra() {
        val texto = _textoLengua.value.trim()
        val trad = _traduccion.value.trim()
        if (texto.isBlank() || trad.isBlank()) return

        val palabra = Palabra(
            id = 0,
            textoLengua = texto,
            traduccion = trad,
            lengua = _lenguaSeleccionada.value,
            categoria = _categoriaSeleccionada.value,
            imagenRes = _imagenResId.value,
            audioPath = _audioPath.value
        )
        repositorio.agregarPalabra(palabra)
        limpiarFormulario()
        refrescarLista()
    }

    private fun limpiarFormulario() {
        _textoLengua.value = ""
        _traduccion.value = ""
        _imagenResId.value = null
        _audioPath.value = null
    }
}
