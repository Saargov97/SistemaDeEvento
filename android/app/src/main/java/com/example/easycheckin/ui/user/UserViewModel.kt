package com.example.easycheckin.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easycheckin.database.UserRepository
import com.example.routemap.database.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val pRes: UserRepository) : ViewModel() {

    val users = pRes.allUsers

    fun insert(u: User) = viewModelScope.launch(Dispatchers.IO) {
        pRes.insert(u)
    }
}