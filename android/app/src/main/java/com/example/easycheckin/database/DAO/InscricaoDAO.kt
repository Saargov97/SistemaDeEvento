package com.example.routemap.database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.routemap.database.model.Inscricao

@Dao
interface InscricaoDAO {

    @Query("SELECT * from inscricao ORDER BY id DESC")
    fun getSavedInscricoes(): LiveData<List<Inscricao>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(inscricao: Inscricao)

    @Query("DELETE FROM inscricao")
    suspend fun deleteAll()
}