package com.example.easycheckin.ui.evento

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easycheckin.database.EventoRepository
import com.example.routemap.database.model.Evento
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventoViewModel @ViewModelInject constructor (private val pRes: EventoRepository) : ViewModel() {

    val eventos = pRes.allEvents

    fun insert(e: Evento) = viewModelScope.launch(Dispatchers.IO) {
        pRes.insert(e)
    }
}