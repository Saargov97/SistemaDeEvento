package com.example.easycheckin.api

class UserModel {
    var id = 0
    var email:String? = null
    var password:String? = null // bcrypt
    var password2:String? = null
    var nom_pessoa:String? = null
    var num_cpf:String? = null
    var createdAt:String? = null
    var updatedAt:String? = null
}