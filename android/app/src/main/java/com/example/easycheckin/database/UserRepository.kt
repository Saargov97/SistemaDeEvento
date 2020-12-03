package com.example.easycheckin.database

import androidx.lifecycle.LiveData
import com.example.easycheckin.data.Result
import com.example.easycheckin.data.model.LoggedInUser
import com.example.routemap.database.DAO.UserDAO
import com.example.routemap.database.model.User
import java.io.IOException
import java.util.*

class UserRepository(private val userDao: UserDAO)  {
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

    /*fun login2(email: String, password: String): Result<User> {
       try {
            val u = userDao.login(email, password)
            // TODO: handle loggedInUser authentication
            return Result.Success(u)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun login(email: String, password: String): Result<User? {
        // handle login
        val result = login2(email, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }*/

    private fun setLoggedInUser(u: User) {
        this.user = u
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}
