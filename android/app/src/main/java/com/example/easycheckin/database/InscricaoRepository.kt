package com.example.easycheckin.database

import androidx.lifecycle.LiveData
import com.example.routemap.database.DAO.InscricaoDAO
import com.example.routemap.database.model.Inscricao
import javax.inject.Inject

class InscricaoRepository @Inject constructor (private val inscricaoDao: InscricaoDAO)  {
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allInscricoes: LiveData<List<Inscricao>> = inscricaoDao.getSavedInscricoes()
    fun allNamedInscricoes(eventoId: Int): LiveData<List<InscricaoDAO.NamedInscricao>> = inscricaoDao.getNamedInscricoes(eventoId)

    suspend fun allSyncInscricoes() = inscricaoDao.getSyncInscricoes()
    suspend fun updateCheckin(id: Int) = inscricaoDao.updateCheckin(id)
    suspend fun updateSync(id: Int) = inscricaoDao.updateSync(id)

    suspend fun insert(position: Inscricao) {
        inscricaoDao.insert(position)
    }
}
