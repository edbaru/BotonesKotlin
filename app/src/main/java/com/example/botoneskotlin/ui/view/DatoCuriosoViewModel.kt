package com.example.botoneskotlin.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.botoneskotlin.datos.api.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DatoCuriosoViewModel : ViewModel() {
    private val _dato = MutableStateFlow("Cargando")
    val dato: StateFlow<String> = _dato

    init {
        fetchDato()
    }

    fun fetchDato(){
        viewModelScope.launch {
            try {
                val resultado = RetrofitInstance.api.getDatoCurioso()
                if (resultado.isNotEmpty()) {
                    _dato.value = resultado[0].fact
                } else {
                    _dato.value = "No se recibió ningún dato"
                }
            } catch (e: Exception) {
                _dato.value = "Error al obtener el dato"
                e.printStackTrace() // Agrega esto para ver el error real en Logcat
            }
        }
    }
}