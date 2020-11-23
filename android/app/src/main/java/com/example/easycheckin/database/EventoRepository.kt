package com.example.easycheckin.database

import androidx.lifecycle.LiveData
import com.example.routemap.database.DAO.EventoDAO
import com.example.routemap.database.model.Evento

class EventoRepository(private val eventoDao: EventoDAO)  {
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allEvents: LiveData<List<Evento>> = eventoDao.getSavedEvents()

    suspend fun insert(evento: Evento) {
        eventoDao.insert(evento)
    }
}
