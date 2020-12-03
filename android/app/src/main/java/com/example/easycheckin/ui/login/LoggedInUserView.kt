package com.example.easycheckin.ui.login

import com.example.routemap.database.model.User

/**
 * User details post authentication that is exposed to the UI
 */
data class LoggedInUserView(
        val displayName: String
        //val user: User
        //... other data fields that may be accessible to the UI
)