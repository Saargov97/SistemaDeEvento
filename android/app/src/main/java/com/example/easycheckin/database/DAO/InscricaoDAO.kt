package com.example.routemap.database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.routemap.database.model.Inscricao
import java.time.LocalDateTime

@Dao
interface InscricaoDAO {

    data class NamedInscricao(
        val id: Int,
        val userId: Int,
        val email: String,
        val nom_pessoa: String? = "Indefinido",
        val ind_checkin: Int
    )

    @Query("SELECT * FROM inscricao ORDER BY id DESC")
    fun getSavedInscricoes(): LiveData<List<Inscricao>>

    @Query(
        value = """
            SELECT I.id, I.userId, U.email, U.nom_pessoa, I.ind_checkin
            FROM inscricao I 
                 LEFT JOIN user U ON (U.id = I.userId) 
            WHERE I.eventoId = :eventoId
            ORDER BY I.id DESC
            """
    )
    fun getNamedInscricoes(eventoId: Int): LiveData<List<NamedInscricao>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(inscricao: Inscricao)

    @Query("DELETE FROM inscricao")
    suspend fun deleteAll()
}