package com.example.routemap.database

import androidx.lifecycle.LiveData
import com.example.routemap.database.DAO.PositionDAO
import com.example.routemap.database.model.Position

class PositionRepository(private val positionDao: PositionDAO)  {
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allPositions: LiveData<List<Position>> = positionDao.getSavedPositions()

    suspend fun insert(position: Position) {
        positionDao.insert(position)
    }
}
