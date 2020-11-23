package com.example.easycheckin.database

import androidx.lifecycle.LiveData
import com.example.routemap.database.DAO.InscricaoDAO
import com.example.routemap.database.model.Inscricao

class InscricaoRepository(private val inscricaoDao: InscricaoDAO)  {
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allUsers: LiveData<List<Inscricao>> = inscricaoDao.getSavedInscricoes()

    suspend fun insert(position: Inscricao) {
        inscricaoDao.insert(position)
    }
}
