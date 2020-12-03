package com.example.routemap.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "password")
    val password: String,
    @ColumnInfo(name = "password2")
    val password2: String,
    @ColumnInfo(name = "nom_pessoa")
    val nom_pessoa: String? = null,
    @ColumnInfo(name = "num_cpf")
    val num_cpf: String? = null,
    @ColumnInfo(name = "ind_atualizado")
    val ind_atualizado: Int? = 0
)