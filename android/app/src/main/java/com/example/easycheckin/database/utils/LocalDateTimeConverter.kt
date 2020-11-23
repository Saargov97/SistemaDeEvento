package com.example.routemap.database.utils

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.LocalDateTime
import java.time.ZoneOffset

class LocalDateTimeConverter {
    @TypeConverter
    fun fromLocalDateTime(localDateTime: LocalDateTime?): Long? =
        localDateTime?.toEpochSecond(ZoneOffset.UTC)

    @TypeConverter
    fun toLocalDateTime(epochSecond: Long?): LocalDateTime? =
        epochSecond?.let { LocalDateTime.ofEpochSecond(it, 0, ZoneOffset.UTC) }
}