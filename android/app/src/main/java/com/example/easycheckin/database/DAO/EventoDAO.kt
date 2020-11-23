package com.example.routemap.database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.routemap.database.model.Evento

@Dao
interface EventoDAO {

    @Query("SELECT * from evento ORDER BY dta_evento DESC")
    fun getSavedEvents(): LiveData<List<Evento>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(evento: Evento)

    @Query("DELETE FROM evento")
    suspend fun deleteAll()
}