package com.example.routemap.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "evento")
data class Evento(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "nom_evento")
    val nom_evento: String? = null,
    @ColumnInfo(name = "dta_evento")
    val dta_evento: String? = null,
    @ColumnInfo(name = "num_vaga")
    val num_vaga: Int,
    @ColumnInfo(name = "vlr_evento")
    val vlr_evento: Double,
    @ColumnInfo(name = "des_carga_horaria")
    val des_carga_horaria: String? = null,
    @ColumnInfo(name = "ind_atualizado")
    val ind_atualizado: Int? = 0
)