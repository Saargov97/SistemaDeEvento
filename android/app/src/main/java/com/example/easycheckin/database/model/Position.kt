package com.example.routemap.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "position_table")
data class Position(
    //@ColumnInfo(name = "seq_position")
    @PrimaryKey(autoGenerate = true)
    val seqPosition: Int? = null,
    @ColumnInfo(name = "dta_data")
    val date: LocalDateTime,
    @ColumnInfo(name = "vlr_longitude")
    val longitude: Double,
    @ColumnInfo(name = "vlr_latitude")
    val latitude: Double)