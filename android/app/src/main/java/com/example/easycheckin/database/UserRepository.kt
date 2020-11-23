package com.example.easycheckin.database

import androidx.lifecycle.LiveData
import com.example.routemap.database.DAO.UserDAO
import com.example.routemap.database.model.User

class UserRepository(private val userDao: UserDAO)  {
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allUsers: LiveData<List<User>> = userDao.getSavedUsers()

    suspend fun insert(position: User) {
        userDao.insert(position)
    }
}
