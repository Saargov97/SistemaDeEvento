package com.example.easycheckin.database

import androidx.lifecycle.LiveData
import com.example.easycheckin.data.Result
import com.example.easycheckin.data.model.LoggedInUser
import com.example.routemap.database.DAO.UserDAO
import com.example.routemap.database.model.User
import java.io.IOException
import java.util.*
import javax.inject.Inject

class UserRepository @Inject constructor (private val userDao: UserDAO)  {
    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allUsers: LiveData<List<User>> = userDao.getSavedUsers()
    // in-memory cache of the loggedInUser object
    var user: User? = null
        private set

    init {
        user = null
    }

    suspend fun insert(position: User) {
        userDao.insert(position)
    }

    suspend fun login(email: String, password: String): Result<User> {
        val result = userDao.login(email, password)

        if (result != null) {
            return Result.Success(result)
        } else {
            return Result.Error(Exception("Usuário não encontrado!"))
        }
    }

    private fun setLoggedInUser(u: User) {
        this.user = u
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
