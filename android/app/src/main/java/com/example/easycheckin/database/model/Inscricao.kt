package com.example.routemap.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inscricao")
data class Inscricao(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "des_qrcode")
    val des_qrcode: String? = null,
    @ColumnInfo(name = "des_hash")
    val des_hash: String? = null,
    @ColumnInfo(name = "ind_checkin")
    val ind_checkin: Int,
    @ColumnInfo(name = "userId")
    val userId: Int,
    @ColumnInfo(name = "eventoId")
    val eventoId: Int,
    @ColumnInfo(name = "ind_atualizado")
    val ind_atualizado: Int? = 0
)