package com.example.routemap.database.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.routemap.database.model.User

@Dao
interface UserDAO {

    @Query("SELECT * from user ORDER BY email DESC")
    fun getSavedUsers(): LiveData<List<User>>

    @Query("SELECT * from user WHERE email = :email AND password2 = :password")
    fun login(email: String, password: String): User?


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteAll()
}