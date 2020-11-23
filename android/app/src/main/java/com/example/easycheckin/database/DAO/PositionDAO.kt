package com.example.routemap.database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.routemap.database.model.Position

@Dao
interface PositionDAO {

    @Query("SELECT * from position_table ORDER BY dta_data DESC")
    fun getSavedPositions(): LiveData<List<Position>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(position: Position)

    @Query("DELETE FROM position_table")
    suspend fun deleteAll()
}